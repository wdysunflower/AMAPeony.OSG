package com.peony.osg.model.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.peony.osg.model.base.ManagerBase;
import com.peony.osg.model.data.DBActivity;
import com.peony.osg.model.data.DBActivityDao;
import com.peony.osg.model.data.DaoMaster;
import com.peony.osg.model.data.DaoSession;

/**
 * Created by wdynetposa on 2015/1/28.
 */
public class ActivityManager extends ManagerBase<DBActivity> {

    public ActivityManager(Context context) {
        super(context);
    }

    @Override
    protected void initData() {
        addActivity();
    }

    public void addActivity() {
        SQLiteDatabase daoDatabase = daoHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(daoDatabase);
        DaoSession daoSession = daoMaster.newSession();
        DBActivityDao noteDao = daoSession.getDBActivityDao();
        DBActivity activity = new DBActivity();
        activity.setName("test" + System.currentTimeMillis());
        noteDao.insert(activity);
    }
}
