package com.peony.osg.view.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.peony.osg.R;
import com.peony.osg.model.manager.ActivityManager;

/**
 * Created by wdynetposa on 2015/1/22.
 */
public class MineActivityFragment extends Fragment {
    View root;
    ActivityManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = new ActivityManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_mine_activity, container, false);
        initView();
        return root;
    }

    private void initView() {
        View btn = root.findViewById(R.id.add_test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //manager.addActivity();
            }
        });
    }
}
