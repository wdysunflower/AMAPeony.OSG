package com.peony.osg.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.peony.osg.log.CLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdynetposa on 2014/12/5.
 */
public abstract class ListBaseAdapter<T1, T2> extends BaseAdapter {
    protected Context mContext;
    protected LayoutInflater mInflater;

    protected List<T1> mSourceList;
    private int pageViewCount = 0;

    protected ListBaseAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mSourceList = new ArrayList<T1>();
    }

    @Override
    public int getCount() {
        if (mSourceList != null) {
            return mSourceList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (mSourceList != null && i < getCount()) {
            return mSourceList.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        T2 holder;

        View contentView = view;
        if (contentView == null) {
            contentView = getView(viewGroup);
            holder = getViewHolder(contentView);
            contentView.setTag(holder);
            pageViewCount++;
            CLog.d("pageViewCount:" + pageViewCount);
        } else {
            holder = (T2) contentView.getTag();
        }

        T1 item = (T1) getItem(position);

        if (item != null && holder != null) {
            setView(position, item, holder);
        }

        return contentView;
    }

    protected abstract View getView(ViewGroup viewGroup);

    protected abstract T2 getViewHolder(View view);

    protected abstract void setView(int position, T1 obj, T2 holder);

    public void clean() {
        mSourceList.clear();
    }

    public void add(T1 obj) {
        mSourceList.add(obj);
        notifyDataSetChanged();
    }

    public void addList(List<T1> list) {
        mSourceList.addAll(list);
        notifyDataSetChanged();
    }
}
