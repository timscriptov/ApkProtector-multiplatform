package mph.trunksku.apps.dexpro.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import mph.trunksku.apps.dexpro.adapter.IgnoredClassAdapter;
import mph.trunksku.apps.dexpro.adapter.SearchAdapter;
import mph.trunksku.apps.dexpro.model.IgnoredClass;

public class IgnoredClassDialog {
    private static List<IgnoredClass> sPermissionList;
    private Activity activity;
    private SearchAdapter adapter;
    private AlertDialog dialog;
    private IgnoredClassView mManifestView;
    private EditText searchView;
    private List<IgnoredClass> selectedList;

    private boolean mode;

    

    class AnonymousClass100000004 implements OnItemLongClickListener {
        private final Activity val$activity;

        class AnonymousClass100000003 implements OnClickListener {
            private final int position;
            private final View view;

            AnonymousClass100000003(int i, View view) {
                this.position = i;
                this.view = view;
            }

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                IgnoredClass item = adapter.getItem(position);
                boolean invertSelection = ((IgnoredClassAdapter.ItemView) view).holder.invertSelection();
                adapter.setSelected(item, invertSelection);
                if (invertSelection) {
                    selectedList.add(item);
                } else {
                    IgnoredClassAdapter.remove(selectedList, item);
                }
            }
        }

        AnonymousClass100000004(Activity activity) {
            this.val$activity = activity;
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            IgnoredClass item = adapter.getItem(i);
            AlertDialog.Builder message = new AlertDialog.Builder(this.val$activity).setTitle(item.getLabel()).setMessage(item.getDescribe());
            CharSequence charSequence =adapter.isSelected(item.getName()) ? "Deselect": "select";
            message.setPositiveButton(charSequence, new AnonymousClass100000003(i, view)).setNegativeButton(17039360, (OnClickListener) null).create().show();
            return true;
        }
    }

    public interface Listener {
        void onAdd(List<IgnoredClass> list, boolean mode);
    }

    private void updateDialogTitle(String title) {
        this.dialog.setTitle(String.format(title + " (%d selected)", new Object[]{new Integer(this.adapter.getSelected().size())}));
    }

    public IgnoredClassDialog(){
        
    }
    
    public void show(final Activity activity, final CheckBox cb, List<IgnoredClass> item, List<IgnoredClass> selected, final boolean ignored, final Listener listener) {
        int i;
        this.activity = activity;
        this.sPermissionList = new ArrayList();
        this.selectedList = new ArrayList();
		selectedList.clear();
        for (i = 0; i < selected.size(); i++) {
            selectedList.add(selected.get(i));
        }
        mode = ignored;
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(1);
        this.searchView = new EditText(activity);
        this.searchView.setHint("Enter search content");
        LinearLayout linearLayout2 = new LinearLayout(activity);
        linearLayout2.setPadding(dp2px((float) 16), 0, dp2px((float) 16), 0);
        linearLayout2.addView(this.searchView, -1, -2);
        linearLayout.addView(linearLayout2, -1, -2);
        this.mManifestView = new IgnoredClassView(activity);
        mManifestView.setDivider((Drawable) null);
        this.mManifestView.setFastScrollEnabled(true);
        linearLayout2 = new LinearLayout(activity);
        linearLayout2.setOrientation(1);
        linearLayout2.addView(this.mManifestView, -1, -2);
        final View view = new View(activity);
        View view2 = new View(activity);
        TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(new int[]{16843564});
        view.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
        view2.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
        obtainStyledAttributes.recycle();
        linearLayout2.addView(view, new LayoutParams(-1, dp2px((float) 1)));
        linearLayout2.addView(view2, new LayoutParams(-1, dp2px((float) 1)));
        view.setVisibility(8);
        view2.setVisibility(8);
        linearLayout.addView(linearLayout2, -1, -2);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        AlertDialog.Builder view3 = builder.setTitle("Add ignored class").setView(linearLayout);
        dialog = view3.setPositiveButton("Save", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    listener.onAdd(selectedList, mode);
                }
            })
            .setNegativeButton(17039360, new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    if(cb != null){
                        cb.setChecked(false);
                    }
                }
            })
            .setNeutralButton("Mode", null)
            .create();
            dialog.show();
            dialog.getButton(-3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View p1) {
                    PopupMenu popupMenu = new PopupMenu(activity, p1);
                    Menu menu = popupMenu.getMenu();
                    menu.add(1, 0, 0, "Ignored").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                mode = true;
                                //this.this$0.toggleList(DependencieDialog.FLAG_SHOW_APPCOMPAT, AnonymousClass100000003.access$0(this.this$0).getAppCompatList());
                                return false;
                            }
                        });
                    menu.add(1, 0, 0, "Encrypted").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                mode = false;
                                //this.this$0.toggleList(DependencieDialog.FLAG_SHOW_ANDROIDX, AnonymousClass100000003.access$0(this.this$0).getAndroidXList());
                                return false;
                            }
                        });
                    menu.setGroupCheckable(1, true, true);
                    
                    popupMenu.show();
                   // menu.getItem(mode).setChecked(true);
                    if (mode) {
                        menu.getItem(0).setChecked(true);
                    } else {
                        menu.getItem(1).setChecked(true);

                    }
                }
            });
        if (sPermissionList == null || sPermissionList.isEmpty()) {
			sPermissionList.clear();
            sPermissionList = item;
            Collections.sort(sPermissionList);
            //sPermissionList = 
        }
        this.adapter = new SearchAdapter(activity, sPermissionList);
        this.adapter.bind(this.searchView);
        this.adapter.setListener(new IgnoredClassAdapter.Listener() {
                @Override
                public void onDataSetChanged() {
                }

                @Override
                public void onSelection(IgnoredClass permission, boolean z) {
                    //updateDialogTitle("");
                }
            });
        this.adapter.setSelectMode(true);
        this.mManifestView.setAdapter(this.adapter);
        this.mManifestView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    IgnoredClass item = adapter.getItem(i);
                    boolean invertSelection = ((IgnoredClassAdapter.ItemView) view).holder.invertSelection();
                    adapter.setSelected(item, invertSelection);
                    if (invertSelection) {
                        selectedList.add(item);
                    } else {
                        IgnoredClassAdapter.remove(selectedList, item);
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        this.mManifestView.setOnItemLongClickListener(new AnonymousClass100000004(activity));
        int i2 = 0;
        int i3 = -1;
        while (i2 < sPermissionList.size()) {
            int i4 = i3;
            for (i = 0; i < selected.size(); i++) {
                if (sPermissionList.get(i2).getName().equals(selected.get(i).getName())) {
                    this.adapter.setSelected(sPermissionList.get(i2), true);
                    if (i4 < 0) {
                        i4 = i2;
                    }
                }
            }
            i2++;
            i3 = i4;
        }
        this.adapter.notifyDataSetChanged();
        if (i3 > 0) {
            this.mManifestView.setSelection(i3);
        }
        updateDialogTitle("Class Filter");
    }



    /*private ArrayList<IgnoredClass> loadPermissions() {
     ArrayList<IgnoredClass> arrayList = new ArrayList();
     arrayList.add(new IgnoredClass("Test"));
     arrayList.add(new IgnoredClass("Test1"));
     Collections.sort(sPermissionList);
     return arrayList;
     }*/

    public static int dp2px(float f) {
        return (int) ((Resources.getSystem().getDisplayMetrics().density * f) + 0.5f);
    }
}

