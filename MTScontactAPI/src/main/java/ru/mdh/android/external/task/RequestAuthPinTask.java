package ru.mdh.android.external.task;

import android.os.AsyncTask;
import ru.mdh.android.configuration.Config;


public class RequestAuthPinTask extends AsyncTask<String, Void, String> {

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
    protected String doInBackground(String[] params) {
        String pin = null;

        String phoneNumber = params[0];

        //send request to server
        //recive response from server

        if(pin == null){
            type.setType(errorType.Type1);//or type.setType(errorType.Type2);
            return null;
        }
        else
            return pin;
    }

    @Override
    protected void onPostExecute(String pin) {
        super.onPostExecute(pin);
        if(pin != null) {
            Config.setPin(pin);
            resultListener.onResult();
        }
        else {
            if(type.getType() == errorType.Type1)
                message = "Type1 happen in RequestAuthPinTask";
            if(type.getType() == errorType.Type2)
                message = "Type2 happen in RequestAuthPinTask";
            resultListener.onError(type, message);
        }

    }


}
