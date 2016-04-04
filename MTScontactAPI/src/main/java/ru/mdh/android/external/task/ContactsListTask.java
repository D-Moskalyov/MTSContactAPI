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
    protected List<Contact> doInBackground(Void... params) {
        
        //publishProgress(...);

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
        else {
            if(type.getType() == errorType.Type1)
                message = "Type1 error happen in ContactsListTask";
            if(type.getType() == errorType.Type2)
                message = "Type2 error happen in ContactsListTask";
            resultListener.onError(type, message);
        }
    }
}
