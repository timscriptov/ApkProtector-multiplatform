package mph.trunksku.apps.dexpro.async;


import android.os.AsyncTask;

public class Worker extends AsyncTask<Void, Integer, String> {
    private WorkerAction workerAction;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        workerAction.run();
    }
    
    @Override
    protected String doInBackground(Void[] p1) {
        workerAction.runFirst();
        return "love";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        workerAction.runLast();
    }

    
    public Worker(WorkerAction work){
        workerAction = work;
    }
    
    public interface WorkerAction {
        void run();
        void runFirst();
        void runLast();
    }
  
}

