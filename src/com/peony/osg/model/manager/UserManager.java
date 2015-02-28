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
    public static void testIsLogin(Context context) {
        Intent intent = null;
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            intent = new Intent(context, MainActivity.class);
        } else {
            intent = new Intent(context, LoginActivity.class);
        }
        if (intent != null) {
            context.startActivity(intent);
        }
    }
}
