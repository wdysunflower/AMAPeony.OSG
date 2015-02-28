package com.peony.osg.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.peony.osg.OSGApplication;
import com.peony.osg.R;
import com.peony.osg.view.fragment.IntroFragment;
import com.peony.osg.view.fragment.SplashFragment;

/**
 * Created by wdynetposa on 2015/2/28.
 */
public class StartPageActivity extends FragmentActivity {
    private Fragment mIntroFragment;
    private Fragment mSplashFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        initView();
    }

    private void initView() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        OSGApplication application = (OSGApplication) getApplication();
        if (application.isFirst()) {
            mIntroFragment = new IntroFragment();
            transaction.replace(R.id.start_fl, mIntroFragment);
        } else {
            mSplashFragment = new SplashFragment();
            transaction.replace(R.id.start_fl, mSplashFragment);
        }
        transaction.commit();
    }
}
