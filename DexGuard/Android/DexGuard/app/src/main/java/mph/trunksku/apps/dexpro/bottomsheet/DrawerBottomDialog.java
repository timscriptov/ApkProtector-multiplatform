package mph.trunksku.apps.dexpro.bottomsheet;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.mph.dexprotect.R;
import java.util.ArrayList;
import mph.trunksku.apps.dexpro.view.BottomSheetListView;

public class DrawerBottomDialog extends BottomSheetDialogFragment {

   // private View mView;
   // ArrayList<HashMap<String, Object>> map = new ArrayList<>();

    private Button btnSub;

    private Button btnShare;

   // private TextView btnTest;

    //private TextView btnTest2;

	private String apkpath;

	private DrawerBottomDialog.Listener listen = null;

    private BottomSheetListView list;

    private ArrayList<DrawerBottomDialog.Items> items;

    public static class Items {

        public int icon = 0;

        public String title;
        
        public Items(int icon, String title){
            this.icon = icon;
            this.title = title;
        }

       
        
        public int getIcon(){
            return icon;
        }
        
        public String getTitle(){
            return title;
        }
    }
    
    public DrawerBottomDialog(String path, Listener listen, ArrayList<Items> items) {
      apkpath = path;
	  this.listen = listen;
      this.items = items;
    }
	
	
	
	public interface Listener{
		public void onItemClick(int pos);
	}
	
	@Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View mView = View.inflate(getContext(), R.layout.layout_bottom_drawer, null);
        dialog.setContentView(mView);
		list = (BottomSheetListView) mView.findViewById(R.id.item_list);
        
        list.setAdapter(new Assets(getActivity(),items));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
                    if(listen != null){
                        listen.onItemClick(p3);
					}
                }
            });
        
		btnSub = (Button) mView.findViewById(R.id.subscribe);
        btnShare = (Button) mView.findViewById(R.id.share);

        btnSub.setBackground(ripple());
        btnShare.setBackground(ripple());

       /* btnTest = (TextView) mView.findViewById(R.id.test);
        btnTest.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View p1) {
                    
					if(listen != null){
						listen.onClick1();
					}
                    dialog.dismiss();
                }
            });
        btnTest2 = (TextView) mView.findViewById(R.id.test2);
        btnTest2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View p1) {
                    
					if(listen != null){
						listen.onClick2();
					}
                    dialog.dismiss();
                }
            });*/
        ((View) mView.getParent()).setBackgroundColor(Color.TRANSPARENT);
        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    BottomSheetDialog d = (BottomSheetDialog) dialog;
                    FrameLayout bottomSheet = (FrameLayout) d.findViewById(R.id.design_bottom_sheet);
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            },0);
    }
    
   
    

    public RippleDrawable ripple() {
        GradientDrawable mask = new android.graphics.drawable.GradientDrawable();
        mask.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
        mask.setColor(0xFF000000); // the color is irrelevant here, only the alpha
        mask.setCornerRadius(5); // you can have a rounded corner for the effect
        ColorStateList rippleColorLst = android.content.res.ColorStateList.valueOf(
            android.graphics.Color.argb(255, 50, 150, 255) // set the color of the ripple effect
        );
        return new android.graphics.drawable.RippleDrawable(rippleColorLst, null, mask);
    }
    
    public class Assets extends BaseAdapter {

        @Override
        public Object getItem(int p1) {
            return null;
        }
        
        
        private ArrayList<DrawerBottomDialog.Items> _data;
        private Context _context;
        
        public Assets(Context context, ArrayList<DrawerBottomDialog.Items> _arr) {
            _context = context;
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

       /* @Override
        public ArrayList<DrawerBottomDialog.Items> getItem(int _index) {
            return _data.get(_index);
        }*/

        @Override
        public long getItemId(int _index) {
            return _index;
        }
        
        @Override
        public View getView(final int _position, View _v, ViewGroup _container) {
            LinearLayout base = new LinearLayout(_context);
            base.setOrientation(0);
            base.setBackground(ripple());
            base.setGravity(Gravity.CENTER_VERTICAL);
            //  base.setPadding(40, 10, 10, 10);
            ImageView icon = new ImageView(_context);
            icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
            icon.setPadding(40, 10, 30, 10);
            base.addView(icon, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            TextView title = new TextView(_context);
            //title.setPadding(10, 0, 0, 0);
            title.setTextSize(17f);
            base.addView(title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            title.setText(_data.get(_position).getTitle());
           
            icon.setImageResource(_data.get(_position).getIcon());
           /* TextView status = new TextView(_context);
            status.setPadding(10, 0, 0, 0);
            status.setTextSize(17f);
            status.setGravity(Gravity.RIGHT);
            base.addView(status, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            status.setText("used");*/

            return base;
        }


	}
}

