package com.peony.osg.model.data;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.peony.osg.model.data.DBActivity;

import com.peony.osg.model.data.DBActivityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dBActivityDaoConfig;

    private final DBActivityDao dBActivityDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dBActivityDaoConfig = daoConfigMap.get(DBActivityDao.class).clone();
        dBActivityDaoConfig.initIdentityScope(type);

        dBActivityDao = new DBActivityDao(dBActivityDaoConfig, this);

        registerDao(DBActivity.class, dBActivityDao);
    }
    
    public void clear() {
        dBActivityDaoConfig.getIdentityScope().clear();
    }

    public DBActivityDao getDBActivityDao() {
        return dBActivityDao;
    }

}