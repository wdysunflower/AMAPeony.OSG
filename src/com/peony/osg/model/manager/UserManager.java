package com.peony.osg.model.manager;

import android.content.Context;
import android.content.Intent;
import com.avos.avoscloud.AVUser;
import com.peony.osg.view.LoginActivity;
import com.peony.osg.view.MainActivity;

/**
 * Created by wdynetposa on 2015/2/28.
 */
public class UserManager {
    public static boolean isLogin() {
        AVUser currentUser = AVUser.getCurrentUser();
        return currentUser != null;
    }
}
