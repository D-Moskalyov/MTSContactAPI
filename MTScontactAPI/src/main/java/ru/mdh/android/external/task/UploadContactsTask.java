package ru.mdh.android.external.task;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import ru.mdh.android.model.Contact;
import ru.mdh.android.model.Photo;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class UploadContactsTask extends AsyncTask<String, Integer, Void>{

    Context context;
    public UploadContactsTask(Context context){
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

        Cursor phones = context.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null,
                null, null);

        publishProgress(1);
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        while (phones.moveToNext()) {
            Contact contact = new Contact();

            contact.setName(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            contact.setNumber(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            contact.setImageUri(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)));
            contact.setContactID(phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID)));

            contactList.add(contact);
        }

        //send contact to server

        publishProgress(2);

        phones.moveToFirst();
        ArrayList<Photo> photos = new ArrayList<Photo>();
        while (phones.moveToNext()) {
            Photo photo = new Photo();

            //String photoURI = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
            String contactID = phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID));

            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactID)));
            Bitmap photoBitmap = BitmapFactory.decodeStream(inputStream);
            photo.setPhoto(photoBitmap);
            photo.setContactID(contactID);

            photos.add(photo);
        }

        //send photo to server

        publishProgress(3);

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
                message = "Type1 error happen in UploadContactsTask";
            if(type.getType() == errorType.Type2)
                message = "Type2 error happen in UploadContactsTask";
            resultListener.onError(type, message);
        }
    }
}
