package com.peony.osg.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.peony.osg.R;
import com.peony.osg.model.manager.UserManager;
import com.peony.osg.view.LoginActivity;
import com.peony.osg.view.MainActivity;
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
    }

    /*
     * 控制跳转至最后一页时出现程序入口
     */
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

                // 区分是否已经登录
                if (UserManager.isLogin()) {
                    root.findViewById(R.id.intro_logged_entry_rl).setVisibility(View.VISIBLE);

                    View enter = root.findViewById(R.id.intro_entry_enter_btn);
                    enter.setOnClickListener(doOnClick);
                } else {
                    root.findViewById(R.id.intro_not_logged_entry_rl).setVisibility(View.VISIBLE);

                    View login = root.findViewById(R.id.intro_entry_login_btn);
                    login.setOnClickListener(doOnClick);

                    View lookAround = root.findViewById(R.id.intro_entry_look_around_btn);
                    lookAround.setOnClickListener(doOnClick);
                }
            }
        }
    };

    /*
     * 按钮动作
     */
    View.OnClickListener doOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.intro_entry_enter_btn:
                    intent = new Intent(getActivity(), MainActivity.class);
                    break;
                case R.id.intro_entry_login_btn:
                    intent = new Intent(getActivity(), LoginActivity.class);
                    break;
                case R.id.intro_entry_look_around_btn:
                    intent = new Intent(getActivity(), MainActivity.class);
                    break;
            }

            if (intent != null) {
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        }
    };
}
