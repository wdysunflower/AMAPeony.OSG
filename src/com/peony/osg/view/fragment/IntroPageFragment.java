package com.peony.osg.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.peony.osg.R;

/**
 * Created by wdynetposa on 2015/2/28.
 */
public class IntroPageFragment extends Fragment {
    private View root;
    private int contentId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_intro_page, container, false);
        initView();
        return root;
    }

    private void initView() {
        root.findViewById(R.id.intro_page_content_view).setBackgroundResource(contentId);
    }

    public static IntroPageFragment newInstance(int index) {
        IntroPageFragment fragment = new IntroPageFragment();
        fragment.setContent(index);
        return fragment;
    }

    private void setContent(int index) {
        switch (index) {
            case 0:
                contentId = R.drawable.intro_page_content_page1;
                break;
            case 1:
                contentId = R.drawable.intro_page_content_page2;
                break;
            case 2:
                contentId = R.drawable.intro_page_content_page3;
                break;
            case 3:
                contentId = R.drawable.intro_entry_bg;
                break;
        }
    }
}
