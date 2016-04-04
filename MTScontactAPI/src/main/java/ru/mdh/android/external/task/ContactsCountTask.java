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
    protected Long doInBackground(String... params) {
        Long count = null;

        //send request to server
        //recive response from server

        if(count == null){
            type.setType(errorType.Type1);//or type.setType(errorType.Type2);
        }

        return  count;
    }

    @Override
    protected void onPostExecute(Long count) {
        super.onPostExecute(count);

        if(count != null)
            resultListener.onResult(count);
        else {
            if(type.getType() == errorType.Type1)
                message = "Type1 error happen in ContactsCountTask";
            if(type.getType() == errorType.Type2)
                message = "Type2 error happen in ContactsCountTask";
            resultListener.onError(type, message);
        }
    }
}
