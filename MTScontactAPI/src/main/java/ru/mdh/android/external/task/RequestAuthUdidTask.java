package ru.mdh.android.external.task;


import android.os.AsyncTask;
import ru.mdh.android.configuration.Config;

public class RequestAuthUdidTask extends AsyncTask<String, Void, String> {
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
    protected String doInBackground(String... params) {
        String udid = null;

        String phoneNumber = params[0];
        String pin = params[1];

        //send request to server
        //recive response from server

        if(udid == null){
            type.setType(errorType.Type1);//or type.setType(errorType.Type2);
            return null;
        }
        else
            return udid;
    }

    @Override
    protected void onPostExecute(String udid) {
        super.onPostExecute(udid);

        if(udid != null) {
            Config.setUdid(udid);
            resultListener.onResult();
        }
        else {
            if(type.getType() == errorType.Type1)
                message = "Type1 error happen in RequestAuthUdidTask";
            if(type.getType() == errorType.Type2)
                message = "Type2 error happen in RequestAuthUdidTask";
            resultListener.onError(type, message);
        }
    }
}
