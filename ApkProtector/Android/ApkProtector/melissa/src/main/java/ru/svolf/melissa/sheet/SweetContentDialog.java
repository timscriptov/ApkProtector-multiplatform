package ru.svolf.melissa.sheet;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import ru.svolf.melissa.R;
import ru.svolf.melissa.data.ControlsItem;
import ru.svolf.melissa.dto.DialogControlsAdapter;
import ru.svolf.melissa.util.Render;


/**
 * Created by Snow Volf on 26.08.2017, 21:38
 */

public class SweetContentDialog extends BottomSheetDialog {
    private Context mContext;
    private FrameLayout mContentFrame;
    private TextView mCaption, mContentView;
    private RecyclerView mControllerView;
    private ArrayList<ControlsItem> mControls = new ArrayList<>();
    private boolean mDismissOnTouch = false;

    public SweetContentDialog(@NonNull Context context) {
        super(context);
        mContext = context;
        initContentView();
    }

    private void initContentView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_simple_content, null);
        mCaption = view.findViewById(R.id.content_caption);
        mContentFrame = view.findViewById(R.id.content_frame);
        mContentView = view.findViewById(R.id.content);
        mControllerView = view.findViewById(R.id.list);
        setContentView(view);
    }

    @Override
    public void setTitle(CharSequence title) {
        mCaption.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        mCaption.setText(titleId);
    }

    public void setMessage(int resId) {
        mContentView.setText(resId);
    }

    public void setMessage(StringBuilder sb) {
        mContentView.setText(sb);
    }

    public void setMessage(CharSequence text) {
        mContentView.setText(text);
    }

    public void setMessage(Spanned text) {
        mContentView.setText(text);
    }

    public void setView(@LayoutRes int resId) {
        setView(LayoutInflater.from(getContext()).inflate(resId, null));
    }

    /**
     * @param activity parent activity
     * @param percent  percentage of the screen height to which the dialog box will be expanded
     */
    public void peekFullScreen(Activity activity, int percent){
        int peekLimit = ((percent * 100) / Render.getScreenHeight(activity));
        getBehavior().setPeekHeight(peekLimit, true);
    }

    public void setPositive(@DrawableRes int resId, CharSequence text, View.OnClickListener listener) {
        mControls.add(new ControlsItem(resId, text, listener));
    }

    public void setNegative(@DrawableRes int resId, CharSequence text, View.OnClickListener listener) {
        mControls.add(new ControlsItem(resId, text, listener));
    }

    public void setNeutral(@DrawableRes int resId, CharSequence text, View.OnClickListener listener) {
        mControls.add(new ControlsItem(resId, text, listener));
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setIcon(@DrawableRes int resId) {
        setIcon(AppCompatResources.getDrawable(mContext, resId));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setIcon(Drawable icon) {
        mCaption.setCompoundDrawablesRelative(icon, null, null, null);
    }

    public void setView(View view) {
        mContentFrame.removeAllViews();
        mContentFrame.addView(view);
    }

    public void makeBlur() {
        if (mContext instanceof Activity) {
            Bitmap screen = Render.takeScreenShot((Activity) mContext);
            Bitmap fast = Render.fastblur(screen, 20, 50);
            final Drawable draw = new BitmapDrawable(mContext.getResources(), fast);
            getWindow().setBackgroundDrawable(draw);
        }
    }

    public void addAction(@DrawableRes int resId, CharSequence text, View.OnClickListener listener) {
        mControls.add(new ControlsItem(resId, text, listener));
    }

    public void setDismissOnTouch(boolean dismissOnTouch) {
        this.mDismissOnTouch = dismissOnTouch;
    }

    @Override
    public void show() {
        super.show();
        if (mControls.size() > 0) {
            final DialogControlsAdapter adapter = new DialogControlsAdapter(mControls);
            adapter.setItemClickListener((menuItem, position) -> {
                if (menuItem.getAction() != null) {
                    menuItem.getAction().onClick(null);
                    if (mDismissOnTouch)
                        dismiss();
                } else {
                    dismiss();
                }
            });
            mControllerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mControllerView.setAdapter(adapter);
        }
    }

    public class Builder {

        public Builder() {
        }

        public Builder setTitle(CharSequence title) {
            setTitle(title);
            return this;
        }

        public Builder setTitle(@StringRes int resId) {
            setTitle(resId);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            setMessage(message);
            return this;
        }

        public Builder setMessage(@StringRes int resId) {
            setMessage(resId);
            return this;
        }

        public Builder setMessage(Spanned text) {
            setMessage(text);
            return this;
        }

        public Builder setMessage(StringBuilder text) {
            setMessage(text);
            return this;
        }

        public Builder makeBlur() {
            makeBlur();
            return this;
        }

        public Builder addAction(@DrawableRes int resId, CharSequence text, View.OnClickListener listener) {
            addAction(resId, text, listener);
            return this;
        }

        public Builder setView(@LayoutRes int resId) {
            SweetContentDialog.this.setView(resId);
            return this;
        }

        public Builder setView(View view) {
            setView(view);
            return this;
        }

        public Builder setIcon(@DrawableRes int resId) {
            setIcon(resId);
            return this;
        }

        public Builder setIcon(Drawable icon) {
            setIcon(icon);
            return this;
        }

        public Builder setDismissible(boolean dismissible) {
            setDismissOnTouch(dismissible);
            return this;
        }

        public SweetContentDialog create() {
            return SweetContentDialog.this;
        }

        public void show() {
            create().show();
        }
    }

    @Override
    public void dismiss() {
        mControls = null;
        mContentFrame = null;
        mCaption = null;
        mControllerView = null;
        super.dismiss();
    }
}
