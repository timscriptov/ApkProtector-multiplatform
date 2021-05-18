package com.mcal.apkprotector.data.dto.applist;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class AppListAdapter extends ArrayAdapter<PackageInfoHolder> {

    public AppListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public AppListAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public AppListAdapter(@NonNull Context context, int resource, @NonNull PackageInfoHolder[] objects) {
        super(context, resource, objects);
    }

    public AppListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull PackageInfoHolder[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public AppListAdapter(@NonNull Context context, int resource, @NonNull List<PackageInfoHolder> objects) {
        super(context, resource, objects);
    }

    public AppListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<PackageInfoHolder> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}