package ru.mdh.android.external.task;


import android.os.AsyncTask;

import java.util.List;

public class DownloadBackupListTask extends AsyncTask<Void, Integer, List<DownloadBackupListTask.BackupItem>> {

    public interface ResultListener {
        void onResult(List<BackupItem> backups);
        void onProgress (int progress);
        void onError(ErrorType type, String message);
    }
    private ResultListener resultListener;
    public void setResultListener (ResultListener rL){
        resultListener = rL;
    }

    public class BackupItem {

    }

    public class ErrorType {

    }

    ErrorType type;
    String message;

    @Override
    protected List<BackupItem> doInBackground(Void... params) {
        return null;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        resultListener.onProgress(values[0]);
    }

    @Override
    protected void onPostExecute(List<BackupItem> backupItems) {
        super.onPostExecute(backupItems);

        if(true)
            resultListener.onResult(backupItems);
        else
            resultListener.onError(type, message);

    }

}
