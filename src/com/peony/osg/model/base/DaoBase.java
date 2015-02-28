package com.peony.osg.model.base;

import android.content.Context;
import com.peony.osg.model.data.DaoMaster;

import java.util.List;

/**
 * Created by wdynetposa on 2015/1/28.
 */
public abstract class DaoBase<T> extends VMBase {
    protected DaoMaster.DevOpenHelper daoHelper;

    protected DaoBase(Context context) {
        super(context);
        daoHelper = new DaoMaster.DevOpenHelper(context, "osg-db", null);
        initData();
    }

    protected abstract void initData();

    protected List<T> mDataList;
}
