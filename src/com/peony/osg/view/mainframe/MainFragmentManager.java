package com.peony.osg.view.mainframe;

import android.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wdynetposa on 2015/3/23.
 */
public class MainFragmentManager {
    private static Map<Class, Fragment> fragmentMap;

    public static Fragment getFragment(Class key) {
        if (fragmentMap == null) {
            fragmentMap = new HashMap<Class, Fragment>();
        } else {
            Fragment oldFragment = fragmentMap.get(key);
            if (oldFragment != null) {
                return oldFragment;
            }
        }

        try {
            Fragment newFragment = (Fragment) key.newInstance();
            return newFragment;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
