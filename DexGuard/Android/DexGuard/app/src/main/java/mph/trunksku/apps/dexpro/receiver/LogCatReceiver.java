package mph.trunksku.apps.dexpro.receiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import mph.trunksku.apps.dexpro.fragment.DebugFragment;
import mph.trunksku.apps.dexpro.logger.Log;
import mph.trunksku.apps.dexpro.logger.DebugLog;
import mph.trunksku.apps.dexpro.logger.ADRTLog;

public class LogCatReceiver extends BroadcastReceiver
{
    StringBuffer sb = new StringBuffer();
    
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String data = intent.getStringExtra("data");
        //
        ADRTLog.i("", data);
       // sb.append(data).append("\n");
       // 
        
        //Log.i("BR" ,"Data received:  " + data);
    }
    
    
}
