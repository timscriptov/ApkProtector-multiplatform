package ru.svolf.melissa;

import android.graphics.Color;

import androidx.annotation.DrawableRes;

public class MainMenuItem {
    private @DrawableRes
    int icon;
    private String title;
    private int action;
    private String tintColor;


    public MainMenuItem(@DrawableRes int icon, String tintColor, String title, int action) {
        this.icon = icon;
        this.tintColor = tintColor;
        this.title = title;
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public int getAction() {
        return action;
    }

    public @DrawableRes
    int getIcon() {
        return icon;
    }

    public int getTintColor() {
        return Color.parseColor(tintColor);
    }
}
