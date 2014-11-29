package com.peony.osg.model;

import android.app.Fragment;

public class Item {

    public int mTitleRes;
    public int mIconRes;
    public Fragment mRelationFragment;

    public Item(int titleRes, int iconRes)
    {
        mTitleRes = titleRes;
        mIconRes = iconRes;
    }

    public Item(int titleRes, int iconRes, Fragment fragment) {
        mTitleRes = titleRes;
        mIconRes = iconRes;
        mRelationFragment = fragment;
    }
}
