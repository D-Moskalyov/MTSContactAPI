package ru.mdh.android.external.task;


import android.os.AsyncTask;

public class SetAuthUdidTask extends AsyncTask<String, Void, Boolean> {

    public interface ResultListener {
        void onResult();
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
    protected Boolean doInBackground(String... params) {

        String phoneNumber = params[0];
        String pin = params[1];
        String udid = params[2];

        boolean requestResaultSuccess = false;
        //send request to server
        //recive response from server

        if(!requestResaultSuccess){
            type.setType(errorType.Type1);//or type.setType(errorType.Type2);
            return false;
        }
        else
            return true;
    }

    @Override
    protected void onPostExecute(Boolean isSuccess) {
        super.onPostExecute(isSuccess);

        if(isSuccess)
            resultListener.onResult();
        else {
            if(type.getType() == errorType.Type1)
                message = "Type1 error happen in SetAuthUdidTask";
            if(type.getType() == errorType.Type2)
                message = "Type2 error happen in SetAuthUdidTask";
            resultListener.onError(type, message);
        }
    }
}
