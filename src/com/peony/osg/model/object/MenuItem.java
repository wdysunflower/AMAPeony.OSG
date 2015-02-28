package com.peony.osg.model.object;

import android.app.Fragment;
import com.peony.osg.model.base.UIBase;

/**
 * 菜单项
 */
public class MenuItem extends UIBase {
    // Res
    public int mIconRes;
    public Fragment mRelationFragment;

    /**
     * Constructor
     *
     * @param titleRes
     * @param iconRes
     */
    public MenuItem(int titleRes, int iconRes) {
        super(titleRes);
        mIconRes = iconRes;
    }

    /**
     * Constructor
     *
     * @param titleRes
     * @param iconRes
     * @param fragment
     */
    public MenuItem(int titleRes, int iconRes, Fragment fragment) {
        super(titleRes);
        mIconRes = iconRes;
        mRelationFragment = fragment;
    }
}
