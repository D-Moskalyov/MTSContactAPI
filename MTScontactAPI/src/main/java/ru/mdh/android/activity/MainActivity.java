package ru.mdh.android.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import ru.mdh.android.external.task.*;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAuth(View view){
        RequestAuthPinTask requestAuthPinTask = new RequestAuthPinTask();
        requestAuthPinTask.setResultListener(new RequestAuthPinTask.ResultListener() {

            @Override
            public void onResult() {
                RequestAuthUdidTask requestAuthUdidTask = new RequestAuthUdidTask();
                requestAuthUdidTask.setResultListener(new RequestAuthUdidTask.ResultListener() {

                    @Override
                    public void onResult() {
                        SetAuthUdidTask setAuthUdidTask = new SetAuthUdidTask();
                        setAuthUdidTask.setResultListener(new SetAuthUdidTask.ResultListener() {
                            @Override
                            public void onResult() {

                            }

                            @Override
                            public void onError(SetAuthUdidTask.ErrorType type, String message) {

                            }
                        });
                        setAuthUdidTask.execute();
                    }

                    @Override
                    public void onError(RequestAuthUdidTask.ErrorType type, String message) {

                    }
                });
                requestAuthUdidTask.execute();
            }

            @Override
            public void onError(RequestAuthPinTask.ErrorType type, String message) {

            }
        });
        requestAuthPinTask.execute();
    }

    public void onClickContactsCount(View view) {
        ContactsCountTask contactsCountTask = new ContactsCountTask();
        contactsCountTask.setResultListener(new ContactsCountTask.ResultListener() {
            @Override
            public void onResult(long count) {

            }

            @Override
            public void onError(ContactsCountTask.ErrorType type, String message) {

            }
        });
        contactsCountTask.execute();
    }

    public void onClickUploadContacts(View view){
        UploadContactsTask uploadContactsTask = new UploadContactsTask();
        uploadContactsTask.setResultListener(new UploadContactsTask.ResultListener() {
            @Override
            public void onResult() {

            }

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onError(UploadContactsTask.ErrorType type, String message) {

            }
        });
        uploadContactsTask.execute();
    }

    public void onClickDownloadContacts(View view){
        DownloadContactsTask downloadContactsTask = new DownloadContactsTask();
        downloadContactsTask.setResultListener(new DownloadContactsTask.ResultListener() {
            @Override
            public void onResult() {

            }

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onError(DownloadContactsTask.ErrorType type, String message) {

            }
        });
        downloadContactsTask.execute();
    }

    public void onClickDownloadBackupList(View view){
        DownloadBackupListTask downloadBackupListTask = new DownloadBackupListTask();
        downloadBackupListTask.setResultListener(new DownloadBackupListTask.ResultListener() {
            @Override
            public void onResult(List<DownloadBackupListTask.BackupItem> backups) {

            }

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onError(DownloadBackupListTask.ErrorType type, String message) {

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

            }
        });
        contactsListTask.execute();
    }

}
