package mph.trunksku.apps.dexpro.adapter;


import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mph.trunksku.apps.dexpro.model.IgnoredClass;

public class IgnoredClassAdapter extends BaseAdapter {
    public Context context;
    private List<IgnoredClass> currentList;
    private Listener listener;
    private boolean selectMode;
    private Map<String, Boolean> selected = new HashMap();

    public static class ItemView extends LinearLayout {
        public ViewHolder holder = new ViewHolder();

        public ItemView(Context context) {
            super(context);
            CheckBox checkBox = new CheckBox(context);
            checkBox.setClickable(false);
            checkBox.setFocusable(false);
            checkBox.setFocusableInTouchMode(false);
            TextView textView = new TextView(context);
            textView.setTextAppearance(context, 16973890);
            textView.setTextSize((float) 15);
            TextView textView2 = new TextView(context);
            textView2.setTextAppearance(context, 16973894);
            textView2.setTextSize((float) 14);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.addView(checkBox);
            linearLayout.setPadding(0, 0, IgnoredClassAdapter.dp2px((float) 16), 0);
            LinearLayout linearLayout2 = new LinearLayout(context);
            linearLayout2.setOrientation(1);
            linearLayout2.addView(textView);
            linearLayout2.addView(textView2);
            setGravity(16);
            setPadding(IgnoredClassAdapter.dp2px((float) 16), IgnoredClassAdapter.dp2px((float) 13), IgnoredClassAdapter.dp2px((float) 16), IgnoredClassAdapter.dp2px((float) 13));
            addView(linearLayout);
            addView(linearLayout2);
            this.holder.cb = checkBox;
            this.holder.title = textView;
            this.holder.subtitle = textView2;
        }
    }

    public interface Listener {
        void onDataSetChanged();

        void onSelection(IgnoredClass permission, boolean z);
    }

    public static class ViewHolder {
        private CheckBox cb;
        public TextView subtitle;
        public TextView title;

        public void setSelection(boolean z) {
            this.cb.setChecked(z);
        }

        public boolean invertSelection() {
            setSelection(!this.cb.isChecked());
            return this.cb.isChecked();
        }

        public void setSelectionMode(boolean z) {
            ((View) this.cb.getParent()).setVisibility(z ? 0 : 8);
        }
    }

    public /* bridge */ Object m0getItem(int i) {
        return getItem(i);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public IgnoredClassAdapter(Context context, List<IgnoredClass> list) {
        this.context = context;
        this.currentList = list;
    }

    public void setSelectMode(boolean z) {
        if (z) {
            if (isSelectMode()) {
                return;
            }
        } else if (!isSelectMode()) {
            return;
        }
        this.selectMode = z;
        getSelected().clear();
    }

    public boolean isSelectMode() {
        return this.selectMode;
    }

    public void setSelected(IgnoredClass permission, boolean z) {
        if (z) {
            getSelected().put(permission.getName(), new Boolean(z));
        } else {
            getSelected().remove(permission.getName());
        }
        if (this.listener != null) {
            this.listener.onSelection(permission, z);
        }
    }

    public void setSelected(Map<String, Boolean> map) {
        this.selected = map;
    }

    public Map<String, Boolean> getSelected() {
        return this.selected;
    }

    @Override
    public void notifyDataSetChanged() {
        if (this.listener != null) {
            this.listener.onDataSetChanged();
        }
        if (getCurrentList().isEmpty()) {
            notifyDataSetInvalidated();
        } else {
            super.notifyDataSetChanged();
        }
    }

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }

    public boolean isSelected(String str) {
        if (this.selected.containsKey(str)) {
            return ((Boolean) this.selected.get(str)).booleanValue();
        }
        return false;
    }

    public static void remove(List<IgnoredClass> list, IgnoredClass permission) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (((IgnoredClass) list.get(size)).getName().equals(permission.getName())) {
                list.remove(size);
            }
        }
    }

    public static void remove(List<IgnoredClass> list, String str) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (((IgnoredClass) list.get(size)).getName().equals(str)) {
                list.remove(size);
            }
        }
    }

    public static boolean hasAdded(List<IgnoredClass> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            if (((IgnoredClass) list.get(i)).getName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView;
        if (view == null) {
            itemView = new ItemView(this.context);
        } else {
            itemView = view;
        }
        ViewHolder viewHolder = ((ItemView) itemView).holder;
        IgnoredClass item = getItem(i);
        viewHolder.title.setText(new SpannableStringBuilder().append(String.format("%d. ", new Object[]{new Integer(i + 1)})).append(getItemTitle(i)));
        viewHolder.subtitle.setText(getItemSubTitle(i));
        viewHolder.setSelection(isSelected(item.getName()));
        viewHolder.setSelectionMode(isSelectMode());
        return itemView;
    }

    public CharSequence getItemTitle(int i) {
        return getItem(i).getLabel();
    }

    public CharSequence getItemSubTitle(int i) {
        return getItem(i).getName();
    }

    public void setCurrentList(List<IgnoredClass> list) {
        this.currentList = list;
    }

    public List<IgnoredClass> getCurrentList() {
        return this.currentList;
    }

    @Override
    public int getCount() {
        return getCurrentList().size();
    }

    @Override
    public IgnoredClass getItem(int i) {
        return (IgnoredClass) getCurrentList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    public static int dp2px(float f) {
        return (int) ((Resources.getSystem().getDisplayMetrics().density * f) + 0.5f);
    }
}

