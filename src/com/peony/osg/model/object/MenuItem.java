package com.peony.osg.model.object;

import android.app.Fragment;
import com.peony.osg.model.base.UIBase;

/**
 * 菜单项
 */
public class MenuItem extends UIBase {
    // Res
    public int mIconRes;
    public Class relationData;

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
     * @param data
     */
    public MenuItem(int titleRes, int iconRes, Class data) {
        super(titleRes);
        mIconRes = iconRes;
        relationData = data;
    }
}
