package com.peony.osg.view.mainframe;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.peony.osg.R;
import com.peony.osg.view.mine.MineTabAdapter;
import com.peony.osg.vpi.TabPageIndicator;

/**
 * Created by wdynetposa on 2014/11/29.
 */
public class MineFragment extends Fragment {
    private FragmentActivity mActivity;
    private View mRoot;
    private ViewPager mPager;
    private TabPageIndicator mIndicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (FragmentActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_mine, container, false);
        initView();
        setView();
        return mRoot;
    }

    private void initView() {
        mPager = (ViewPager) mRoot.findViewById(R.id.mine_data_pager);
        mIndicator = (TabPageIndicator) mRoot.findViewById(R.id.mine_data_indicator);
    }

    private void setView() {
        FragmentStatePagerAdapter adapter = new MineTabAdapter(mActivity);
        mPager.setAdapter(adapter);
        mIndicator.setViewPager(mPager);

        mPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }
}
