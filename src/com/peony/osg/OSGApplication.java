package com.peony.osg;

import android.app.Application;
import android.content.res.Resources;

/**
 * Created by wdynetposa on 2014/12/1.
 */
public class OSGApplication extends Application {

    public static Resources appResource;

    @Override
    public void onCreate() {
        super.onCreate();
        appResource = getApplicationContext().getResources();
    }
}
