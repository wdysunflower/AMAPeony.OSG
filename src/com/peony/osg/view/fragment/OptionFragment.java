package com.peony.osg.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.peony.osg.R;

/**
 * Created by wdynetposa on 2014/11/29.
 */
public class OptionFragment extends Fragment {
    private View mRoot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_option, container, false);
        setView();
        return mRoot;
    }

    private void setView() {
    }
}