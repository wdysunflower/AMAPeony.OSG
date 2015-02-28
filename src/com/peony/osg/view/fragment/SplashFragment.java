package com.peony.osg.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.peony.osg.R;
import com.peony.osg.model.manager.UserManager;

/**
 * Created by wdynetposa on 2015/2/28.
 */
public class SplashFragment extends Fragment {
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_splash, container, false);
        iniView();
        return root;
    }

    private void iniView() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UserManager.testIsLogin(getActivity());
                getActivity().finish();
            }
        }, 3000);
    }
}
