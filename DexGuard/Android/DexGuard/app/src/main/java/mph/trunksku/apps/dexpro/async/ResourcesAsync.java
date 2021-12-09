package mph.trunksku.apps.dexpro.async;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedInputStream;

public class ResourcesAsync extends AsyncTask<String, String, String> {
    private static final String TAG = "Download";

    private Context context;

    private Listener listener;
    private PowerManager.WakeLock wakeLock;

    
    private InputStream is;

    private BufferedReader buffer;


    private ProgressDialog pd = null;

    private String url;

    private boolean ePd = false;

    public interface Listener {
        void onCompleted(String config);

        void onCancelled();

        void onException(String ex);
    }

    public ResourcesAsync(Context context, boolean on, String url, Listener listener) {
        this.context = context;
        this.ePd = on;
        this.listener = listener;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        if (ePd) {
            pd = new ProgressDialog(context);
            pd.setMessage("Updating, Please wait...");
            pd.show();
        }
    }

    @Override
    protected String doInBackground(String... args) {
        try {
            String api = url;
            if (!api.startsWith("http")) {
                api = new StringBuilder().append("http://").append(url).toString();
            }
            
                URL url = new URL(api);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                // read the response
                InputStream in = new BufferedInputStream(conn.getInputStream());
                return convertStreamToString(in);
           
        } catch (Exception e) {
            return "error:" + e.getMessage();
        } /*finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException ignored) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {

                }
            }
            
        }*/
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.i(TAG, "Cancelled");
        if(ePd){
            pd.dismiss();
        }
        listener.onCancelled();
    }

    @Override
    protected void onPostExecute(String result) {
        // wakeLock.release();
        //nm.cancel(1);
        if(ePd){
            pd.dismiss();
        }
        if (result.startsWith("error:")) {
            listener.onException(result.split(":")[1]);
        } else
            listener.onCompleted(result);
    }
    
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}




