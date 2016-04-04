package ru.mdh.android.external.task;


import android.os.AsyncTask;

import java.util.List;

public class ContactsListTask extends AsyncTask<Void, Integer, List<ContactsListTask.Contact>> {
    public interface ResultListener {
        void onResult(List<Contact> backups);
        void onProgress (int progress);
        void onError(ErrorType type, String message);
    }
    private ResultListener resultListener;
    public void setResultListener (ResultListener rL){
        resultListener = rL;
    }

    public class Contact {

    }

    public class ErrorType {

    }

    ErrorType type;
    String message;

    @Override
    protected List<Contact> doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        resultListener.onProgress(values[0]);
    }

    @Override
    protected void onPostExecute(List<Contact> contacts) {
        super.onPostExecute(contacts);

        if(true)
            resultListener.onResult(contacts);
        else
            resultListener.onError(type, message);
    }
}
