package com.peony.osg.view.widget.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.peony.osg.R;

public class ResideMenuItem extends LinearLayout {

    /** menu item icon */
    private ImageView iv_icon;
    /** menu item title */
    private TextView tv_title;

    public ResideMenuItem(Context context) {
        super(context);
        initViews(context);
    }

    public ResideMenuItem(Context context, int icon, int title) {
        super(context);
        initViews(context);
        setViews(icon, title);
    }

    private void initViews(Context context) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_row_item, this);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    /**
     * set the icon color;
     *
     * @param icon
     */
    public void setIcon(int icon) {
        iv_icon.setImageResource(icon);
    }

    /**
     * set the title with resource ;
     * 
     * @param title
     */
    public void setTitle(int title) {
        tv_title.setText(title);
    }

    /**
     * set the title with string;
     *
     * @param title
     */
    public void setTitle(String title) {
        tv_title.setText(title);
    }

    private void setViews(int icon, int title) {
        iv_icon.setImageResource(icon);

        if (title < 0) {
            RelativeLayout.LayoutParams layoutParams =
                    (RelativeLayout.LayoutParams) iv_icon.getLayoutParams();
            layoutParams.width = LayoutParams.WRAP_CONTENT;
            layoutParams.height = LayoutParams.WRAP_CONTENT;
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            iv_icon.setLayoutParams(layoutParams);
            tv_title.setVisibility(GONE);
        } else {
            tv_title.setText(title);
        }
    }
}
