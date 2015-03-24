package com.peony.osg;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import com.avos.avoscloud.AVOSCloud;
import com.peony.osg.util.ConstDefine;
import com.peony.osg.util.PreferencesHelper;

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

    /*
     * 通过比较versionCode及本地的IS_FIRST_SHOW字段来判断是否是第一次运行
     */
    public Boolean isFirstShow(Context context) {
        PackageInfo info = null;
        try {
            PackageManager manager = context.getPackageManager();
            String packageName = context.getPackageName();
            info = manager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int currentVersion = info.versionCode;
        int version = PreferencesHelper.getAppData(context, ConstDefine.RUNTIME_VERSION, 0);

        if (version != currentVersion) {
            PreferencesHelper.setAppData(context, ConstDefine.RUNTIME_VERSION, currentVersion);
            PreferencesHelper.setAppData(context, ConstDefine.IS_FIRST_SHOW, true);
            return true;
        }

        Boolean isFirst = PreferencesHelper.getAppData(context, ConstDefine.IS_FIRST_SHOW, true);
        return isFirst;
    }
}
