package mph.trunksku.apps.dexpro.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 */
public class AlertDialogUtils {

    public static void alertDialog( Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void alertDialog(Context context, int titleId, int messageId, int buttonLabelId) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        if (titleId != 0) alertDialogBuilder.setTitle(titleId);
        if (messageId != 0) alertDialogBuilder.setMessage(messageId);
        alertDialogBuilder.setPositiveButton(buttonLabelId, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

