package com.peony.osg.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.peony.osg.R;
import com.peony.osg.model.object.Catalog;
import com.peony.osg.view.holder.CatalogHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdynetposa on 2014/11/29.
 */
public class CatalogAdapter extends BaseExpandableListAdapter {

    private ArrayList<Catalog> mGroupList;
    private ArrayList<List<Catalog>> mChildList;

    private Context context;
    private LayoutInflater inflater;

    public CatalogAdapter(Context context, ArrayList<Catalog> groupList,
            ArrayList<List<Catalog>> childList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        mGroupList = groupList;
        mChildList = childList;
    }

    // 返回父列表个数
    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }

    // 返回子列表个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        CatalogHolder catalogHolder = null;
        if (convertView == null) {
            catalogHolder = new CatalogHolder();
            convertView = inflater.inflate(R.layout.catalog_row_group, null);
            catalogHolder.titleText = (TextView) convertView.findViewById(R.id.group_tv);
            convertView.setTag(catalogHolder);
        } else {
            catalogHolder = (CatalogHolder) convertView.getTag();
        }

        catalogHolder.titleText.setText(((Catalog) getGroup(groupPosition)).mTitleRes);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        CatalogHolder childHolder = null;
        if (convertView == null) {
            childHolder = new CatalogHolder();
            convertView = inflater.inflate(R.layout.catalog_row_child, null);
            childHolder.titleText = (TextView) convertView.findViewById(R.id.catalog_tv);
            convertView.setTag(childHolder);
        } else {
            childHolder = (CatalogHolder) convertView.getTag();
        }

        childHolder.titleText.setText(((Catalog) getChild(groupPosition, childPosition)).mTitleRes);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
