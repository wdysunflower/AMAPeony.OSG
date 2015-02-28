package com.peony.osg.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.peony.osg.view.fragment.IntroPageFragment;

/**
 * Created by wdynetposa on 2015/2/28.
 */
public class IntroPageAdapter extends FragmentPagerAdapter {

    private int count = 1;

    public IntroPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Fragment getItem(int i) {
        return IntroPageFragment.newInstance(i);
    }
}
