package com.peony.osg.util;

import android.content.res.Resources;
import com.peony.osg.OSGApplication;

/**
 * Created by wdynetposa on 2014/12/1.
 */
public class GlobalMethod {
    public static int getStringIdentifier(String resStr) {
        int id = OSGApplication.appResource.getIdentifier(resStr, "string", "com.peony.osg");
        return id;
    }
}
