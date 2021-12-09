package mph.trunksku.apps.dexpro.fragment;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.mph.dexprotect.R;
import java.io.PrintStream;
import java.util.Timer;
import mph.trunksku.apps.dexpro.logger.ADRTLog;
import mph.trunksku.apps.dexpro.logger.TextPrintStream;
import mph.trunksku.apps.dexpro.receiver.LogCatReceiver;
import mph.trunksku.apps.dexpro.view.CodeView;

public class DebugFragment extends Fragment  {
    public static StringBuffer logs = new StringBuffer();
    
    
    
    private View mView;

    private LogCatReceiver MyReceiver;

    public static CodeView debug;

    private ImageView imageMenu;

    private Timer mTimer;

	private LinearLayout debugLay;

    private Thread t;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyReceiver = new LogCatReceiver();
        IntentFilter intentFilter = new IntentFilter("dexpro.LOGCAT");
        if (intentFilter != null) {
            getActivity().registerReceiver(MyReceiver, intentFilter);
        }

        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_debuger, container, false);
        debug = (CodeView) mView.findViewById(R.id.logs);
	    debug.setFocusable(false);
        debug.setFocusableInTouchMode(false);
        debugLay = (LinearLayout) mView.findViewById(R.id.debugLay);
        imageMenu = (ImageView) mView.findViewById(R.id.logcatHeaderMenuButton);
        imageMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View p1) {
                     PopupMenu popup = new PopupMenu(getActivity(), p1);
					 popup.getMenu().add("Refresh");
					 popup.getMenu().add("Clear");
					 popup.show();
					popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
							@Override
							public boolean onMenuItemClick(MenuItem p1) {
								switch(p1.getTitle().toString()){
									case "Refresh":
										//refresh();
										//debug.setText(Html.fromHtml(ADRTLog.dump()));
										return true;
									case "Clear":
										debug.getEditableText().clear();
										//refresh();
										return true;
									default:
										return true;
								}
								//return false;
							}
					});
                    //debug.setText(Html.fromHtml(ADR.dump()));
                    
                }
            });
		Handler handler = new Handler()
        {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    debug.append(msg.obj.toString());
                    int offset = debug.getLineCount() * debug.getLineHeight()
                        - debug.getHeight();
                    if (offset > 0) {
                        // iscroll.scrollTo(0, iscroll.getBottom());
                        debug.scrollTo(0, offset);
                        //vsb_out.setVisibility(View.VISIBLE);
                    } else {
                        //vsb_out.setVisibility(View.GONE);
                    }
                    
                }
            }
        };

        PrintStream ps = new PrintStream(new TextPrintStream(handler));
        System.setOut(ps);
		System.setErr(ps);
       /* mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    new Thread(new Runnable() {
                            @Override
                            public void run() {
                                debug.setText(Html.fromHtml(DebugLog.dump()));
                            }
                        }).start();
                }
            }, 3000, 3000);*/
	 /*t = new Thread() {

			@Override
			public void run() {
				try {
					while (!isInterrupted()) {
						Thread.sleep(1000);
						getActivity().runOnUiThread(new Runnable() {
								@Override
								public void run() {
									//refresh();
									debug.setText(ADRTLog.dump());
								}
							});
					}
				} catch (InterruptedException e) {
				}
			}
		};

		t.start();*/
		//new Thread(logger, "LogCat").start();
        
        ADRTLog.i("", "<run the app to see its log output>");
        
		return mView;
    }

	public void refresh(){
        
		/*debugLay.removeAllViews();
		//LinearLayout ll = new LinearLayout(context);
		TreeNode root = TreeNode.root();
		TreeNode computerRoot = null;
		String[] log = ADRTLog.dump().split("<br>");
		for ( int i = 0; i < log.length;  i++ ){

			if(log[i].startsWith("> ")){
				computerRoot = new TreeNode(log[i]);
				root.addChildren(computerRoot);
			}else{
				TreeNode file1 = new TreeNode(log[i]);
				if(computerRoot == null){
					root.addChildren(file1);
				}else{
					computerRoot.addChildren(file1);
				}

			}
		}
		/* TreeNode computerRoot = new TreeNode("Root");

		 //TreeNode myDocuments = new TreeNode("Doc");
		 TreeNode downloads = new TreeNode("Download");
		 TreeNode file1 = new TreeNode("FIle");
		 TreeNode file2 = new TreeNode("File 2");

		 downloads.addChildren(file1, file2);

		 TreeNode myMedia = new TreeNode("Media");
		 TreeNode photo1 = new TreeNode("File");
		 myMedia.addChildren(photo1);

		 //myDocuments.addChild(downloads);
		 computerRoot.addChildren(downloads, myMedia);

		 root.addChildren(computerRoot);*/

      /*  AndroidTreeView tView = new AndroidTreeView(getActivity(), root);
        tView.setDefaultAnimation(true);
        //tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
		debugLay.addView(tView.getView());*/
	}
	
	public Runnable logger = new Runnable(){
        @Override
        public void run() {

            debug.setText(Html.fromHtml(ADRTLog.dump()));
        }

    };
	
    @Override
    public void onDestroy() {
        super.onDestroy();
       // t.destroy();
        debug.getEditableText().clear();
        
        if (MyReceiver != null)
            getActivity().unregisterReceiver(MyReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        
        
    }
    
    


}
