package ru.mdh.android.external.task;


import android.os.AsyncTask;

public class DownloadBackupContactsTask extends AsyncTask <Void, Integer, Void> {

    public interface ResultListener {
        void onResult();
        void onProgress (int progress);
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
    protected Void doInBackground(Void... params) {
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

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        resultListener.onProgress(values[0]);
    }
}
