package com.peony.osg.view.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.peony.osg.R;
import com.peony.osg.model.object.Category;
import com.peony.osg.model.object.MenuItem;

import java.util.List;

public class MenuAdapter extends BaseAdapter {

    public interface MenuListener {

        void onActiveViewChanged(View v);
    }

    private Context mContext;

    private List<Object> mItems;

    private MenuListener mListener;

    private int mActivePosition = -1;

    public MenuAdapter(Context context, List<Object> items) {
        mContext = context;
        mItems = items;
    }

    public void setListener(MenuListener listener) {
        mListener = listener;
    }

    public void setActivePosition(int activePosition) {
        mActivePosition = activePosition;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position) instanceof MenuItem ? 0 : 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position) instanceof MenuItem;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View contentView = convertView;
        Object item = getItem(position);

        if (item instanceof Category) {
            if (contentView == null) {
                contentView = LayoutInflater.from(mContext).inflate(R.layout.catalog_row_child, parent, false);
            }

            ((TextView) contentView).setText(((Category) item).mTitleRes);

        } else {
            if (contentView == null) {
                contentView = LayoutInflater.from(mContext).inflate(R.layout.menu_row_item, parent, false);
            }

            TextView tv = (TextView) contentView;
            tv.setText(((MenuItem) item).mTitleRes);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                tv.setCompoundDrawablesRelativeWithIntrinsicBounds(((MenuItem) item).mIconRes, 0, 0, 0);
            } else {
                tv.setCompoundDrawablesWithIntrinsicBounds(((MenuItem) item).mIconRes, 0, 0, 0);
            }
        }
        if (position == mActivePosition) {
            mListener.onActiveViewChanged(contentView);
        }

        return contentView;
    }
}
