package com.peony.osg.model.base;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.peony.osg.OSGApplication;
import com.peony.osg.model.data.DaoMaster;

import java.util.List;

/**
 * Created by wdynetposa on 2015/1/28.
 */
public abstract class ManagerBase<T> {
    protected DaoMaster.DevOpenHelper daoHelper;

    public ManagerBase(Context context) {
        daoHelper = new DaoMaster.DevOpenHelper(context, "osg-db", null);
        initData();
    }

    protected abstract void initData();

    protected List<T> mDataList;
}
