package com.peony.osg;

import android.app.Application;
import android.content.res.Resources;
import com.avos.avoscloud.AVOSCloud;

/**
 * Created by wdynetposa on 2014/12/1.
 */
public class OSGApplication extends Application {

    public static Resources appResource;

    @Override
    public void onCreate() {
        super.onCreate();
        appResource = getApplicationContext().getResources();
        AVOSCloud.initialize(this, "zr6dbnbl7v2ha6lgdmnaenxeqm5avgcn6g65pqdxqdr1ng5n",
                "ahg5nklyr5fupp29x6akc43yb7s93u0saychasf65beadtpk");
    }

    public boolean isFirst() {
        return false;
    }
}
