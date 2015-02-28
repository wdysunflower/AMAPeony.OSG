package com.peony.osg.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.peony.osg.R;
import com.peony.osg.model.manager.UserManager;
import com.peony.osg.view.adapter.IntroPageAdapter;
import com.peony.osg.vpi.CirclePageIndicator;

/**
 * Created by wdynetposa on 2015/2/28.
 */
public class IntroFragment extends Fragment {
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_intro, container, false);
        initView();
        return root;
    }

    private void initView() {
        FragmentActivity activity = (FragmentActivity) getActivity();
        IntroPageAdapter adapter = new IntroPageAdapter(activity.getSupportFragmentManager());
        adapter.setCount(4);

        ViewPager mPager = (ViewPager) root.findViewById(R.id.intro_pager);
        mPager.setAdapter(adapter);

        CirclePageIndicator indicator =
                (CirclePageIndicator) root.findViewById(R.id.intro_indicator);
        indicator.setSpaceMultiple(5);
        indicator.setViewPager(mPager);
        indicator.setOnPageChangeListener(pageChange);

        View enter = root.findViewById(R.id.intro_entry_enter_btn);
        enter.setOnClickListener(enterClick);
    }

    ViewPager.OnPageChangeListener pageChange = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 3) {
                root.findViewById(R.id.intro_indicator).setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            if (root.findViewById(R.id.intro_indicator).getVisibility() == View.GONE) {
                root.findViewById(R.id.intro_page_rl).setVisibility(View.GONE);
                root.findViewById(R.id.intro_entry_rl).setVisibility(View.VISIBLE);
            }
        }
    };

    View.OnClickListener enterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserManager.testIsLogin(getActivity());
            getActivity().finish();
        }
    };
}
