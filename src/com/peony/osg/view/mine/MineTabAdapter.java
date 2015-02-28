package com.peony.osg.view.mine;

import android.app.Activity;
import android.support.v4.app.*;
import com.peony.osg.log.CLog;
import com.peony.osg.util.GlobalMethod;
import com.peony.osg.view.mine.MineActivityFragment;
import com.peony.osg.view.mine.MineFavoritesFragment;
import com.peony.osg.view.mine.MineRoadBookFragment;

/**
 * Created by wdynetposa on 2015/1/22.
 */
public class MineTabAdapter extends FragmentStatePagerAdapter {
    private static final String[] CONTENT = new String[] {"activity", "road_book", "favorites"};

    private Activity mActivity;
    private Fragment mActivityFragment;
    private Fragment mRoadBookFragment;
    private Fragment mFavoritesFragment;

    public MineTabAdapter(FragmentActivity activity) {
        super(activity.getSupportFragmentManager());
        mActivity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (mActivityFragment == null) {
                    mActivityFragment = new MineActivityFragment();
                }
                return mActivityFragment;
            case 1:
                if (mRoadBookFragment == null) {
                    mRoadBookFragment = new MineRoadBookFragment();
                }
                return mRoadBookFragment;
            case 2:
                if (mFavoritesFragment == null) {
                    mFavoritesFragment = new MineFavoritesFragment();
                }
                return mFavoritesFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "mine_" + CONTENT[position % CONTENT.length] + "_tab_title";
        int id = GlobalMethod.getStringIdentifier(title);
        String string = mActivity.getResources().getString(id);
        CLog.d("title:" + string);
        return string;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
