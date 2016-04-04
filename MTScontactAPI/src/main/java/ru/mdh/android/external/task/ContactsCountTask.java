package ru.mdh.android.external.task;


import android.os.AsyncTask;

public class ContactsCountTask extends AsyncTask<String, Void, Long> {
    public interface ResultListener {
        void onResult(long count);
        void onError(ErrorType type, String message);
    }
    private ResultListener resultListener;
    public void setResultListener (ResultListener rL){
        resultListener = rL;
    }
    public class ErrorType {

    }

    ErrorType type;
    String message;

    @Override
    protected Long doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);

        if(true)
            resultListener.onResult(aLong);
        else
            resultListener.onError(type, message);
    }
}
