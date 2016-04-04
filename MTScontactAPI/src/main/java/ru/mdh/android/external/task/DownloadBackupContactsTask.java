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
    protected Void doInBackground(Void... params) {

        //publishProgress(...);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(true)
            resultListener.onResult();
        else {
            if(type.getType() == errorType.Type1)
                message = "Type1 error happen in DownloadBackupContactsTask";
            if(type.getType() == errorType.Type2)
                message = "Type2 error happen in DownloadBackupContactsTask";
            resultListener.onError(type, message);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        resultListener.onProgress(values[0]);
    }
}
