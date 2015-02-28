package com.peony.osg.model.manager;

import android.content.Context;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.peony.osg.model.base.VMBase;

/**
 * Created by wdynetposa on 2015/2/28.
 */
public class SignUpManager extends VMBase {

    protected SignUpManager(Context context) {
        super(context);
    }

    public void signUp(String username, String password, SignUpCallback callback) {
        AVUser user = new AVUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(callback);
    }
}
