package mph.trunksku.apps.dexpro.admob;
import android.content.*;
import android.content.pm.*;
import android.widget.*;

public class AdmobIds {
    public static String online_update= "https://pastebin.com/raw/ktTQaJ1z";

	private Boolean debug;

	private Context context;

	private Boolean showBoolean = false;
	
	public AdmobIds(Context context){
		this.context = context;
		this.debug = isDebug();
	}
	
	public AdmobIds(Context context, Boolean showToast){
		this.context = context;
		this.debug = isDebug();
		this.showBoolean = showToast;
	}
	
    public  String appId(){
		if(debug){
			if(showBoolean){
				Toast.makeText(context, "Debug: Enabled", 1).show();
			}
			return "ca-app-pub-3940256099942544~3347511713";
		}
		if(showBoolean){
			Toast.makeText(context, "Debug: Disabled", 1).show();
		}
		return "ca-app-pub-8356679492114459~6132439483";
	}
	
	public  String adInter(){
		if(debug){
			return "ca-app-pub-3940256099942544/1033173712";
		}
		return "ca-app-pub-8356679492114459/3683543576";
	}
	
	public  String adBanner(){
		if(debug){
			return "ca-app-pub-3940256099942544/6300978111";
		}
		return "ca-app-pub-8356679492114459/6501278608";
	}
	
	public  String adReward(){
		if(debug){
			return "ca-app-pub-3940256099942544/5224354917";
		}
		return "ca-app-pub-8356679492114459/2178890214";
	}
	
	public boolean isDebug() {
		return ((context.getApplicationInfo().flags 
            & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
	}
}

