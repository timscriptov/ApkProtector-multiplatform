package mph.trunksku.apps.dexpro.view;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableStringBuilder;
import android.util.TypedValue;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.text.Html;
import android.view.View.OnClickListener;


public class LogDialog {
    public static void show(Context context, String title, String str, String btnN, DialogInterface.OnClickListener di) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        ScrollView scrollView = new ScrollView(context);
        scrollView.setLayoutParams(layoutParams);
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
        horizontalScrollView.setLayoutParams(layoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(layoutParams);
        horizontalScrollView.addView(linearLayout);
        scrollView.addView(horizontalScrollView);
        linearLayout.addView(a(context, str));
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setView(scrollView);
        builder.setPositiveButton(btnN, di);
        //builder.setNegativeButton("CANCEL", (DialogInterface.OnClickListener) null);
        builder.create().show();
    }

    public static TextView a(Context context, String spannableStringBuilder) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setPadding(getDip(context, 8), getDip(context, 12), getDip(context, 8), getDip(context, 12));
        //textView.setTextColor(-16777216);
        textView.setText(Html.fromHtml(spannableStringBuilder));
        textView.setTextSize(14.0f);
        textView.setHorizontalScrollBarEnabled(true);
        textView.setTextIsSelectable(true);
        return textView;
    }

    public static int getDip(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }
}

