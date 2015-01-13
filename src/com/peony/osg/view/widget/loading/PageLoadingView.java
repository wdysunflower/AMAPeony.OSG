package com.peony.osg.view.widget.loading;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.peony.osg.R;

/**
 * Created by wdynetposa on 2014/12/5.
 */
public class PageLoadingView extends LinearLayout {

    public PageLoadingView(Context context) {
        super(context);
        init();
    }

    public PageLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.widget_page_loading_view, this);
    }
}

