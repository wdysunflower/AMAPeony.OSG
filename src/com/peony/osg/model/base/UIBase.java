package com.peony.osg.model.base;

import com.peony.osg.OSGApplication;

import java.io.Serializable;

/**
 * Model层对象抽象类，主要用于处理都需要用到的资源
 */
public abstract class UIBase implements Serializable {
    // Res
    public int mTitleRes;

    // Property
    public String Title;

    /**
     * Constructor
     * 
     * @param titleRes
     */
    protected UIBase(int titleRes) {
        this.mTitleRes = titleRes;
        if (mTitleRes > 0) {
            Title = OSGApplication.appResource.getString(mTitleRes);
        }
    }
}
