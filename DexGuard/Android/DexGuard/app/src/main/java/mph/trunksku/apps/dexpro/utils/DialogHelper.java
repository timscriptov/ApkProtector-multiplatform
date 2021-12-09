package mph.trunksku.apps.dexpro.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

public class DialogHelper {

    private Context mContext;
    
    public DialogHelper(Context context){
        mContext = context;
    }
    
    public void showBottomDialog(Dialog dialog) {
        dialog.show();
        customDialog(dialog);
    }

    public void customDialog(Dialog dialog) {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (isLandscape()) {
            attributes.width = getScreenWidth() / 2;
            attributes.gravity = 81;
        } else {
            attributes.width = getScreenWidth();
            attributes.gravity = 80;
        }
        window.setAttributes(attributes);
        TypedArray obtainStyledAttributes = dialog.getContext().obtainStyledAttributes(new int[]{16842801});
        window.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
        obtainStyledAttributes.recycle();
    }
    
    public boolean isLandscape() {
        return mContext.getResources().getConfiguration().orientation == 2;
    }
    
    public int getScreenWidth() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }
    
}

