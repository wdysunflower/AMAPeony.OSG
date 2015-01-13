package com.peony.osg.model.manager;

import android.content.res.TypedArray;
import com.peony.osg.OSGApplication;
import com.peony.osg.R;
import com.peony.osg.model.object.Catalog;
import com.peony.osg.util.GlobalMethod;
import com.peony.osg.view.activity.TxtReaderActivity;

import java.util.*;

/**
 * Created by wdynetposa on 2014/12/1.
 */
public class CatalogManager {

    public ArrayList<Catalog> mGroupList;
    public ArrayList<List<Catalog>> mChildList;

    private int mGroupPosition;
    private int mChildPosition;

    public CatalogManager() {
        initData();
    }

    private void initData() {
        mGroupList = new ArrayList<Catalog>();
        mChildList = new ArrayList<List<Catalog>>();

        // 读取二维数组
        TypedArray headerArray =
                OSGApplication.appResource.obtainTypedArray(R.array.catalog_header);
        for (int i = 0; i < headerArray.length(); i++) {
            int resID = headerArray.getResourceId(i, -1);
            String resName = OSGApplication.appResource.getResourceEntryName(resID);
            String resTitleResStr = resName + "_header";
            int resKey = GlobalMethod.getStringIdentifier(resTitleResStr);
            Catalog group = new Catalog(resKey);

            // 读取string数组
            TypedArray titleArray = OSGApplication.appResource.obtainTypedArray(resID);
            List<Catalog> list = new ArrayList<Catalog>();
            for (int j = 0; j < titleArray.length(); j++) {
                Catalog child = new Catalog(titleArray.getResourceId(j, -1));
                list.add(child);
            }
            mChildList.add(list);
            mGroupList.add(group);
        }
    }

    public TxtReaderActivity.OnChangePageListener onChangPageEvent = new TxtReaderActivity.OnChangePageListener() {
        @Override
        public void setReader(TxtReaderActivity reader) {

        }

        @Override
        public void prePage() {

        }

        @Override
        public void nextPage() {

        }
    };
}
