package ru.mdh.android.activity;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;
import ru.mdh.android.configuration.Config;
import ru.mdh.android.external.task.*;
import ru.mdh.android.model.BackupItem;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Config.setAddress("address");
        Config.setPort(1225);
    }



    public void onClickAuth(View view){
        boolean isSetAuthUdidSuccess = false;
        try {
            isSetAuthUdidSuccess = SetAuthUdid();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        }

        if(!isSetAuthUdidSuccess)
            RequestAuthPin();
    }

    public void onClickContactsCount(View view) {
        ContactsCountTask contactsCountTask = new ContactsCountTask();
        contactsCountTask.setResultListener(new ContactsCountTask.ResultListener() {
            @Override
            public void onResult(long count) {
                Toast.makeText(getApplicationContext(), String.valueOf(count) + " contacts", Toast.LENGTH_SHORT).show();
                Config.setContactCountInMasterCopy(count);
            }

            @Override
            public void onError(ContactsCountTask.ErrorType type, String message) {
                Toast.makeText(getApplicationContext(), type + ": " + message, Toast.LENGTH_SHORT).show();
            }
        });
        contactsCountTask.execute();
    }

    public void onClickUploadContacts(View view){
        UploadContactsTask uploadContactsTask = new UploadContactsTask(this);
        uploadContactsTask.setResultListener(new UploadContactsTask.ResultListener() {
            @Override
            public void onResult() {
                Toast.makeText(getApplicationContext(), "upload contacts complete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgress(int progress) {
                switch (progress){
                    case 1:
                        Toast.makeText(getApplicationContext(), "Contact prepare complete", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "Contact upload complete", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), "Contact photo upload complete", Toast.LENGTH_SHORT).show();
                        break;
                    default: break;
                }
            }

            @Override
            public void onError(UploadContactsTask.ErrorType type, String message) {
                Toast.makeText(getApplicationContext(), type + ": " + message, Toast.LENGTH_SHORT).show();
            }
        });
        uploadContactsTask.execute(Config.getUdid());
    }

    public void onClickDownloadContacts(View view){
        DownloadContactsTask downloadContactsTask = new DownloadContactsTask(this);
        downloadContactsTask.setResultListener(new DownloadContactsTask.ResultListener() {
            @Override
            public void onResult() {

            }

            @Override
            public void onProgress(int progress) {
                switch (progress){
                    case 1:
                        Toast.makeText(getApplicationContext(), "Contact get from server", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "Contact photo get from server", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), "Clear contact in phone", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(), "Save contact from master", Toast.LENGTH_SHORT).show();
                        break;
                    default: break;
                }
            }

            @Override
            public void onError(DownloadContactsTask.ErrorType type, String message) {
                Toast.makeText(getApplicationContext(), type + ": " + message, Toast.LENGTH_SHORT).show();
            }
        });
        downloadContactsTask.execute();
    }

    public void onClickDownloadBackupList(View view){
        DownloadBackupListTask downloadBackupListTask = new DownloadBackupListTask();
        downloadBackupListTask.setResultListener(new DownloadBackupListTask.ResultListener() {
            @Override
            public void onResult(List<BackupItem> backups) {

            }

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onError(DownloadBackupListTask.ErrorType type, String message) {
                Toast.makeText(getApplicationContext(), type + ": " + message, Toast.LENGTH_SHORT).show();
            }
        });
        downloadBackupListTask.execute();
    }

    public void onClickDownloadBackupContacts(View view){
        DownloadBackupContactsTask downloadBackupContactsTask = new DownloadBackupContactsTask();
        downloadBackupContactsTask.setResultListener(new DownloadBackupContactsTask.ResultListener() {
            @Override
            public void onResult() {

            }

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onError(DownloadBackupContactsTask.ErrorType type, String message) {
                Toast.makeText(getApplicationContext(), type + ": " + message, Toast.LENGTH_SHORT).show();
            }
        });
        downloadBackupContactsTask.execute();
    }

    public void onClickContactsList(View view){
        ContactsListTask contactsListTask = new ContactsListTask();
        contactsListTask.setResultListener(new ContactsListTask.ResultListener() {
            @Override
            public void onResult(List<ContactsListTask.Contact> contacts) {

            }

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onError(ContactsListTask.ErrorType type, String message) {
                Toast.makeText(getApplicationContext(), type + ": " + message, Toast.LENGTH_SHORT).show();
            }
        });
        contactsListTask.execute();
    }



    boolean SetAuthUdid() throws Exception {
        SetAuthUdidTask setAuthUdidTask = new SetAuthUdidTask();
        setAuthUdidTask.setResultListener(new SetAuthUdidTask.ResultListener() {
            @Override
            public void onResult() {
                Toast.makeText(getApplicationContext(), "SetAuthUdid success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SetAuthUdidTask.ErrorType type, String message) {
                Toast.makeText(getApplicationContext(), type + ": " + message, Toast.LENGTH_SHORT).show();

            }
        });
        setAuthUdidTask.execute(Config.getPhoneNumber(), Config.getPin(), Config.getUdid());

        return setAuthUdidTask.get(10, TimeUnit.SECONDS);
    }

    void RequestAuthPin(){
        TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        Config.setPhoneNumber(tMgr.getLine1Number());

        RequestAuthPinTask requestAuthPinTask = new RequestAuthPinTask();
        requestAuthPinTask.setResultListener(new RequestAuthPinTask.ResultListener() {

            @Override
            public void onResult() {
                Toast.makeText(getApplicationContext(), "RequestAuthPin success", Toast.LENGTH_SHORT).show();
                RequestAuthUdid();
            }

            @Override
            public void onError(RequestAuthPinTask.ErrorType type, String message) {
                Toast.makeText(getApplicationContext(), type + ": " + message, Toast.LENGTH_SHORT).show();
            }
        });
        requestAuthPinTask.execute(Config.getPhoneNumber());
    }

    void RequestAuthUdid(){
        RequestAuthUdidTask requestAuthUdidTask = new RequestAuthUdidTask();
        requestAuthUdidTask.setResultListener(new RequestAuthUdidTask.ResultListener() {

            @Override
            public void onResult() {
                Toast.makeText(getApplicationContext(), "RequestAuthUdid success", Toast.LENGTH_SHORT).show();
                try {
                    SetAuthUdid();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onError(RequestAuthUdidTask.ErrorType type, String message) {
                Toast.makeText(getApplicationContext(), type + ": " + message, Toast.LENGTH_SHORT).show();
            }
        });
        requestAuthUdidTask.execute(Config.getPhoneNumber(), Config.getPin());
    }
}
