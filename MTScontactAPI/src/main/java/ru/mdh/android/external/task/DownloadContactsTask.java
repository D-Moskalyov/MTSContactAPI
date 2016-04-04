package ru.mdh.android.external.task;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import ru.mdh.android.model.Contact;
import ru.mdh.android.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class DownloadContactsTask extends AsyncTask<String, Integer, Void>{

    Context context;
    public DownloadContactsTask(Context context){
        this.context = context;
    }

    public interface ResultListener {
        void onResult();
        void onProgress (int progress);
        void onError(ErrorType type, String message);
    }
    private ResultListener resultListener;
    public void setResultListener (ResultListener rL){
        resultListener = rL;
    }

    enum errorType {Type1, Type2};
    public class ErrorType {
        errorType type;

        public errorType getType() {
            return type;
        }

        public void setType(errorType type) {
            this.type = type;
        }
    }

    ErrorType type;
    String message;

    @Override
    protected Void doInBackground(String... params) {
        String udid = params[0];
        ContentResolver cr = context.getContentResolver();

        ArrayList<Contact> contacts = new ArrayList<Contact>();
        //getContactFromServer
        publishProgress(1);

        ArrayList<Photo> photos = new ArrayList<Photo>();
        //getPhotoFromServer
        publishProgress(2);


        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cur.moveToNext()) {
            try{
                String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
                cr.delete(uri, null, null);
            }
            catch(Exception e)
            {
                System.out.println(e.getStackTrace());
            }
        }
        publishProgress(3);


        for (Contact contact : contacts){
            ContentValues cv = new ContentValues();
            cv.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, contact.getName());
            cv.put(ContactsContract.CommonDataKinds.Phone.NUMBER, contact.getNumber());
            cv.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);

            cr.insert(ContactsContract.RawContacts.CONTENT_URI, cv);
        }

        //save photo from photos
        publishProgress(4);

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        resultListener.onProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(type.getType() != errorType.Type1 && type.getType() != errorType.Type2)
            resultListener.onResult();
        else {
            if(type.getType() == errorType.Type1)
                message = "Type1 error happen in DownloadContactsTask";
            if(type.getType() == errorType.Type2)
                message = "Type2 error happen in DownloadContactsTask";
            resultListener.onError(type, message);
        }
    }

}
