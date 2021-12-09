package mph.trunksku.apps.dexpro.view;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Spanned;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class CustomCKDialog
{
    private Context context;
    
    private AlertDialog.Builder adbuilder;

    private AlertDialog alertdialog;

    private CheckBox checkBox;

    
    public CustomCKDialog(Context context)
    {
        this.context = context;
        adbuilder = new AlertDialog.Builder(context);
        
    }
    
    public boolean isChecked() {
        return checkBox.isChecked();
    }

    public CustomCKDialog setTitle(String title)
    {
        if (title != null){
            adbuilder.setTitle(title);
        }
        return this;
    }

    public CustomCKDialog setCBMessage(String message, CheckBox.OnCheckedChangeListener listener)
    {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(1);
        ll.setPadding(30, 5, 30, 0);
        checkBox = new CheckBox(context);
        checkBox.setOnCheckedChangeListener(listener);
        ll.addView(checkBox);
        checkBox.setText(message);
        adbuilder.setView(ll);
        return this;
    }
    
    public CustomCKDialog setMessage(Spanned message)
    {
        if(message != null) {
            adbuilder.setMessage(message);
        }
        return this;
    }

    public CustomCKDialog setMessage(String message)
    {
        if(message != null) {
            adbuilder.setMessage(message);
        }
        return this;
    }

    public CustomCKDialog setPositive(String ptitle, DialogInterface.OnClickListener listener)
    {
        adbuilder.setPositiveButton(ptitle, listener);
        return this;
    }

    public CustomCKDialog setNegative(String ptitle, DialogInterface.OnClickListener listener)
    {
        adbuilder.setNegativeButton(ptitle, listener);
        return this;
    }
    
    public CustomCKDialog setNeutral(String ptitle, DialogInterface.OnClickListener listener)
    {
        adbuilder.setNeutralButton(ptitle, listener);
        return this;
    }
    
    public CustomCKDialog create()
    {
        alertdialog = adbuilder.create();
        return this;
    }

    public void show()
    {
        alertdialog.show();
    }
}
