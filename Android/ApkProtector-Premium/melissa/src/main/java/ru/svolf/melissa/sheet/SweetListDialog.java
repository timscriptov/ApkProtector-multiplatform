package ru.svolf.melissa.sheet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import ru.svolf.melissa.R;

/**
 * Created by Snow Volf on 20.08.2017, 19:56
 */

public class SweetListDialog extends BottomSheetDialog {
    private Context mContext;
    private TextView mTitle;
    private ListView mList;
    private String[] mItems;
    private ArrayAdapter<String> mAdapter;

    public SweetListDialog(@NonNull Context context) {
        super(context);
        mContext = context;
        initContentView();
    }

    private void initContentView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_list, null);
        mTitle = view.findViewById(R.id.title);
        mList = view.findViewById(R.id.listview);
        setContentView(view);
    }

    public void setTitle(CharSequence title) {
        mTitle.setText(title);
    }

    public void setTitle(@StringRes int title) {
        mTitle.setText(title);
    }

    public void setItems(@ArrayRes int resId) {
        mItems = mContext.getResources().getStringArray(resId);
    }

    public void setItems(String[] items) {
        mItems = items;
    }

    public void setItems(ArrayList<String> items) {
        mItems = items.toArray(new String[0]);
    }

    public void setOnItemsClickListener(AdapterView.OnItemClickListener listener) {
        mList.setOnItemClickListener(listener);
    }

    public ListView getListView() {
        return mList;
    }

    private void setAdapter() {
        mAdapter = new ArrayAdapter<>(mContext, R.layout.menu_row, R.id.list_item_content, mItems);
        mList.setAdapter(mAdapter);
    }

    /**
     * Start the dialog and display it on screen.  The window is placed in the
     * application layer and opaque.  Note that you should not override this
     * method to do initialization when the dialog is shown, instead implement
     * that in {@link #onStart}.
     */
    @Override
    public void show() {
        if (mAdapter == null) {
            setAdapter();
        }
        super.show();
    }

    public class Builder {

        public Builder() {
        }

        public Builder setTitle(CharSequence title) {
            SweetListDialog.this.setTitle(title);
            return this;
        }

        public Builder setTitle(@StringRes int title) {
            SweetListDialog.this.setTitle(title);
            return this;
        }

        public Builder setItems(@ArrayRes int resId) {
            SweetListDialog.this.setItems(resId);
            return this;
        }

        public Builder setItems(String[] items) {
            SweetListDialog.this.setItems(items);
            return this;
        }

        public Builder setItems(ArrayList<String> items) {
            SweetListDialog.this.setItems(items);
            return this;
        }

        public Builder setOnItemsClickListener(AdapterView.OnItemClickListener listener) {
            SweetListDialog.this.setOnItemsClickListener(listener);
            return this;
        }

        public void show() {
            SweetListDialog.this.show();
        }
    }
}
