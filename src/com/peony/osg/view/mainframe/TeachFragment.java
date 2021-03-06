package com.peony.osg.view.mainframe;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.peony.osg.R;
import com.peony.osg.model.manager.CatalogManager;
import com.peony.osg.model.object.Catalog;
import com.peony.osg.view.activity.TxtReaderActivity;
import com.peony.osg.view.adapter.CatalogAdapter;

/**
 * Created by wdynetposa on 2014/11/29.
 */
public class TeachFragment extends Fragment {

    private Context mContext;
    private View mRoot;
    //private PinnedHeaderExpandableListView mDirListView;

    private CatalogManager mCatalogM;
    private CatalogAdapter mCatalogA;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_teach, container, false);
        setView();
        initManager();
        return mRoot;
    }

    private void setView() {
//        mDirListView = (PinnedHeaderExpandableListView) mRoot.findViewById(R.id.dir_lv);
//        mDirListView.setOnChildClickListener(onChildClick);
    }

    private void initManager() {
        // mCatalogM = new CatalogManager();
        // mCatalogA = new CatalogAdapter(mContext, mCatalogM.mGroupList, mCatalogM.mChildList);
        // mDirListView.setAdapter(mCatalogA);
        // for (int i = 0, count = mDirListView.getCount(); i < count; i++) {
        // mDirListView.expandGroup(i);
        // }
    }

    ExpandableListView.OnChildClickListener onChildClick =
            new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView expandableListView, View view,
                        int p1, int p2, long l) {
                    Catalog catalog = (Catalog) mCatalogA.getChild(p1, p2);
                    Intent intent = new Intent(getActivity(), TxtReaderActivity.class);
                    intent.putExtra("catalog", catalog);
                    startActivity(intent);
                    return false;
                }
            };
}
