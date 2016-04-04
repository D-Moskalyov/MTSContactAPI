package ru.mdh.android.external.task;


import android.os.AsyncTask;

public class SetAuthUdidTask extends AsyncTask<String, Void, Void> {

    public interface ResultListener {
        void onResult();
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
    protected Void doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(true)
            resultListener.onResult();
        else
            resultListener.onError(type, message);
    }
}
