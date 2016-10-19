package com.mvp.features.main;

import android.support.v4.app.Fragment;

/**
 * Created by Vinh.Tran on 9/27/16.
 */
public class TabInfo {
    private String title;
    private int iconRes;
    private Class<?> fragment;

    public TabInfo(String title, int iconRes, Class<? extends Fragment> fragment) {
        this.title = title;
        this.iconRes = iconRes;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public int getIconRes() {
        return iconRes;
    }

    public Class<?> getFragment() {
        return fragment;
    }
}
