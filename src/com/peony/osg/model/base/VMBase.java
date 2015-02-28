package com.peony.osg.model.base;

import android.content.Context;

/**
 * Created by wdynetposa on 2015/2/28.
 */
public abstract class VMBase {
    protected Context mContext;

    protected VMBase(Context context) {
        mContext = context;
    }
}
