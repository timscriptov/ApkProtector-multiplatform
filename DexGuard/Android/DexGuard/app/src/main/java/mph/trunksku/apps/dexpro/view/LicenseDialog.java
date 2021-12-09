package mph.trunksku.apps.dexpro.view;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class LicenseDialog
{

	private Context context;

	private AlertDialog mAlert;
	public LicenseDialog(Context c){
		context = c;
		ScrollView sv = new ScrollView(c);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout ll = new LinearLayout(c);
        ll.setOrientation(1);
		ll.setPadding(25, 0, 25, 0);
        ll.setLayoutParams(layoutParams);
		final TextInputLayout til = new TextInputLayout(c);
		final AppCompatEditText acet = new AppCompatEditText(c);
		acet.setHint("License");
		acet.setSingleLine(true);
		til.addView(acet);
		ll.addView(til);
		sv.addView(ll);
		mAlert = new AlertDialog.Builder(c)
			.setTitle("License Checker")
			.setMessage("Enter a valid license")
			.setView(sv)
			.setPositiveButton("CHECK", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
				}
			})
			.setNeutralButton("GET License", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
				}
			})
			.setNegativeButton("CANCEL", null)
			.create();
	}
	
	public void show()
	{
		mAlert.show();
	}
}
