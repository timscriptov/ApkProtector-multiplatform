package mph.trunksku.apps.dexpro;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.mph.dexprotect.R;
import java.util.ArrayList;
import java.util.HashMap;
import mph.trunksku.apps.dexpro.async.ResourcesAsync;
import mph.trunksku.apps.dexpro.utils.ThemeManager;
import mph.trunksku.apps.dexpro.view.CenteredToolBar;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import mph.trunksku.apps.dexpro.async.DownloadAsync;
import android.widget.RelativeLayout;
import mph.trunksku.apps.dexpro.admob.AppLovinHelper;

public class SDKManager extends AppCompatActivity implements OnItemClickListener {


	private ArrayList<String> file_list = new ArrayList<>();
	ArrayList<HashMap<String, Object>> map = new ArrayList<>();

	private LinearLayout base_list;

	private ListView list;

	private LinearLayout base_text;

	private AppLovinHelper adhelp;
	@Override
	public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
		final HashMap<String, Object> mData = map.get(p3);
		//HashMap<String, Object> test = (HashMap<String, Object>) p1.getItemAtPosition(p3) ;
		//Toast.makeText(SDKManager.this, map.get(p3).get("key").toString(), 1).show();
		if (new File(getFilesDir().getAbsolutePath() + "/" + mData.get("outname").toString()).exists()) {
			Toast.makeText(SDKManager.this, "Already Installed!".toString(), 1).show();

		} else {
			adhelp.setRewardedListener(new AppLovinHelper.RewardedListener(){

					@Override
					public void onLoad() {

					}

					@Override
					public void onLoaded() {
					}

					@Override
					public void onReward() {
						new DownloadAsync(SDKManager.this, true, "https://raw.githubusercontent.com/denverku/DexProtector-Core/main/"+mData.get("path").toString(), new File(getFilesDir().getAbsolutePath() + "/" + mData.get("outname").toString()), new DownloadAsync.Listener(){
								@Override
								public void onCompleted(String config) {
									Toast.makeText(SDKManager.this, config.toString(), 1).show();
									fetchData();
								}

								@Override
								public void onCancelled() {
								}

								@Override
								public void onException(String ex) {
									Toast.makeText(SDKManager.this, ex, 1).show();

								}
							}).execute();
					}

					@Override
					public void onClose() {
					}

					@Override
					public void onFail() {
					}
				});
			adhelp.showRewarded();
		}
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		new ThemeManager(this, MyApp.getDefSharedPreferences()).init();
		LinearLayout base = new LinearLayout(this);
		base.setOrientation(1);
		base.setGravity(Gravity.CENTER);
		//base.setBackgroundColor(Color.WHITE);
		CenteredToolBar tb = new CenteredToolBar(this);
		base.addView(tb);
		setSupportActionBar(tb);
		base_text = new LinearLayout(this);
		base_text.setGravity(Gravity.CENTER);
		//base_text.setBackgroundColor(Color.WHITE);
		TextView txt_dev = new TextView(this);

		txt_dev.setAlpha(0.8f);
		txt_dev.setGravity(Gravity.CENTER);
		base_text.addView(txt_dev, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		base.addView(base_text, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		base_list = new LinearLayout(this);
		base_list.setGravity(Gravity.CENTER);
		//base_list.setBackgroundColor(Color.WHITE);
		list = new ListView(this);
        list.setDivider(null);
		//list.setChoiceMode(ListView.CHOICE_MODE_NONE);
		//refreshList(base_text, base_list, list, txt_dev);
		//list.setAdapter(new Assets(map));
		list.setOnItemClickListener(this);
		base_list.addView(list, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		//base.addView(base_list, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);


		RelativeLayout rl = new RelativeLayout(this);
        base.addView(rl);
		rl.addView(base_list);
		RelativeLayout adview = new RelativeLayout(this);
        rl.addView(adview);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -2);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        //params.addRule(RelativeLayout.LEFT_OF, R.id.id_to_be_left_of);

        adview.setLayoutParams(params); 
		setContentView(base);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	    getSupportActionBar().setTitle("SDK Manager");

		fetchData();

		adhelp = new AppLovinHelper(this);
		adhelp.setBannerId("52adea43e0c3fad2");
		adhelp.setBannerView(adview);
		adhelp.setIntertitialId("a2724846654b5280");
		adhelp.setRewardedId("7a5710ab4a75cbdf");
		
		adhelp.build();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 0, Menu.NONE, "Reset").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){

				@Override
				public boolean onMenuItemClick(MenuItem item) {
					new File(getFilesDir().getAbsolutePath() + "/dexpro.jar").delete();
					fetchData();
					return false;
				}


			});

		menu.add(Menu.NONE, 1, Menu.NONE, "Refresh").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){

				@Override
				public boolean onMenuItemClick(MenuItem item) {
					//View v = getContentView();
					fetchData();
					return false;
				}

			});
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		switch (menuItem.getItemId()) {
			case 16908332:
				finish();
				break;
		}
		return super.onOptionsItemSelected(menuItem);
	}

    public void fetchData() {
		new ResourcesAsync(this, true, "https://raw.githubusercontent.com/denverku/DexProtector-Core/main/config.json", new ResourcesAsync.Listener(){

				@Override
				public void onCompleted(String config) {
					//Toast.makeText(SDKManager.this, config, 1).show();
					try {
						JSONArray jar = new JSONObject(config).getJSONArray("CORES");
						map.clear();


						for (int i = 0; i < (int)(jar.length()); i++) {

							JSONObject obj = jar.getJSONObject(i);
							HashMap<String, Object> _item = new HashMap<>();
							_item.put("name", obj.getString("Name"));
							_item.put("version", obj.getString("version"));
							_item.put("path", obj.getString("path"));
							_item.put("outname", obj.getString("outname"));
							
							map.add(_item);


						}
						list.setAdapter(new Assets(map));

						((BaseAdapter)list.getAdapter()).notifyDataSetChanged();
						base_text.setVisibility(View.GONE);
						base_list.setVisibility(View.VISIBLE);

					} catch (Exception e) {
						Toast.makeText(SDKManager.this, e.getMessage(), 1).show();
					}
				}

				@Override
				public void onCancelled() {
				}

				@Override
				public void onException(String ex) {
					Toast.makeText(SDKManager.this, ex, 1).show();
				}
			}).execute();
	}


	public class Assets extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Assets(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@Override
		public int getCount() {
			return _data.size();
		}

		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}

		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			HashMap<String, Object> mData = _data.get(_position);

			FrameLayout fl = new FrameLayout(SDKManager.this);
            CardView cv = new CardView(SDKManager.this);
            //cv.setForeground(R.attr.selectableItemBackground);
            LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,      
                LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 10, 10, 10);
            cv.setLayoutParams(params);
            LinearLayout base = new LinearLayout(SDKManager.this);
			base.setOrientation(0);
			//base.setBackgroundColor(Color.WHITE);
			base.setGravity(Gravity.CENTER_VERTICAL);
			//base.setPadding(10, 10, 10, 10);
			ImageView icon = new ImageView(SDKManager.this);
			icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
            icon.setPadding(10, 10, 10, 10);
			base.addView(icon, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

			TextView title = new TextView(SDKManager.this);
			//title.setPadding(10, 0, 0, 0);
			//title.settextf
			title.setTextSize(15f);
			base.addView(title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			title.setText(mData.get("name").toString() + " - " + mData.get("version").toString());
			/*if (FileUtil.isDirectory(file_list.get((int)(_position)))) {
			 icon.setImageResource(0x7F070049);
			 } else {
			 icon.setImageResource(0x7F070047);
			 }*/
			icon.setImageResource(R.drawable.ic_launcher);
			TextView status = new TextView(SDKManager.this);
			status.setPadding(0, 0, 10, 0);
			//status.setTextSize(17f);
			status.setGravity(Gravity.RIGHT);
			base.addView(status, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			if (new File(getFilesDir().getAbsolutePath() + "/" + mData.get("outname").toString()).exists()) {
				status.setText("Installed");
			} else {
				status.setText("Install");
			}

			cv.addView(base);
            fl.addView(cv);
			return fl;
		}


	}


}
