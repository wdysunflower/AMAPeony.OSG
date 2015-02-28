package com.peony.osg.model.manager;

import android.content.Context;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.peony.osg.model.base.VMBase;

/**
 * Created by wdynetposa on 2015/2/28.
 */
public class LoginManager extends VMBase {
    protected LoginManager(Context context) {
        super(context);
    }

    public void login(String username, String password, LogInCallback callback) {
        AVUser.logInInBackground(username, password, callback);
    }
}
