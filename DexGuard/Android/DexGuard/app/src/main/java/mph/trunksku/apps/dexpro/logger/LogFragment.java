package mph.trunksku.apps.dexpro.logger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import java.text.SimpleDateFormat;

/**
 * Simple fraggment which contains a LogView and uses is to output log data it receives
 * through the LogNode interface.
 */
public class LogFragment extends Fragment {

    private LogView mLogView;
    private LinearLayout mScrollView;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a");

	public LogFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mScrollView = new LinearLayout(getActivity());
		mScrollView.setOrientation(LinearLayout.VERTICAL);
        LayoutParams scrollParams = new LayoutParams(-1, -1);
        mScrollView.setLayoutParams(scrollParams);
        mLogView = new LogView(getActivity());
        LayoutParams logParams = new LayoutParams(scrollParams);
        logParams.height = logParams.MATCH_PARENT;
        mLogView.setLayoutParams(logParams);
        mLogView.setClickable(true);
        mLogView.setFocusable(true);
		RelativeLayout ll = new RelativeLayout(getActivity());
		mScrollView.addView(ll);
        mScrollView.addView(mLogView);
		
        
		return mScrollView;
    }

    public LogView getLogView() {
        return mLogView;
    }
	
	public static void addLog(String str)
	{
		Log.d("|", new StringBuffer().append(str).toString());
	}
	
	public void onStart() {
        super.onStart();
        LogWrapper logWrapper = new LogWrapper();
		Log.setLogNode(logWrapper);
		MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
		logWrapper.setNext(msgFilter);
		// this.logFragment = (LogFragment) getSupportFragmentManager().findFragmentById(R.id.log_window);
		msgFilter.setNext(getLogView());
	}
}
