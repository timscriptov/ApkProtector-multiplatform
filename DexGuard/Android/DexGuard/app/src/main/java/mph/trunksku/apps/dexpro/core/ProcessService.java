package mph.trunksku.apps.dexpro.core;

import android.app.*;
import android.content.*;
import android.os.*;

@SuppressWarnings({"ConstantConditions", "ResultOfMethodCallIgnored"})
public class ProcessService extends Service
{

	@Override
	public IBinder onBind(Intent p1)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		// TODO: Implement this method
		String action = intent.getAction();
		switch(action){
			case "START":
				runprocess();
				break;
			case "STOP":
				break;
		}
		return 1;
	}
	
	void runprocess() {
		(new Thread(new Runnable() {
                @Override
                public void run() {
					
				}
		})).start();
	}
}
