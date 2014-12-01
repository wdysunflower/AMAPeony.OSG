package com.peony.osg.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.peony.osg.R;
import com.peony.osg.model.object.MenuItem;
import com.peony.osg.util.LogHelper;
import com.peony.osg.view.adapter.MenuAdapter;
import com.peony.osg.view.fragment.*;
import com.peony.osg.view.widget.draglayout.DragLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdynetposa on 2014/11/26.
 */
public class MainActivity extends Activity {

    private DragLayout mDragLayout;
    // private FrameLayout mMainLayout;

    private ListView mMenuList;

    private MenuAdapter mMenuAdapter;

    private Fragment mCLFragment;
    private Fragment mMNFragment;
    private Fragment mMOSFragment;
    private Fragment mUTFragment;
    private Fragment mOptionFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMainFragment();
        initMenuListView();
        initDragLayout();
    }

    private void initDragLayout() {
        mDragLayout = (DragLayout) findViewById(R.id.main_dl);
        mDragLayout.setDragListener(new DragLayout.DragListener() {
            @Override
            public void onOpen() {

            }

            @Override
            public void onClose() {

            }

            @Override
            public void onDrag(float percent) {

            }
        });
    }

    private void initMenuListView() {
        List<Object> items = new ArrayList<Object>();
        items.add(new MenuItem(R.string.menu_common_knowledge, R.drawable.ic_menu_common,
                mCLFragment));
        items.add(new MenuItem(R.string.menu_map_navigate, R.drawable.ic_menu_common, mMNFragment));
        items.add(new MenuItem(R.string.menu_utility_tool, R.drawable.ic_menu_common, mUTFragment));
        items.add(new MenuItem(R.string.menu_my_outdoor_sport, R.drawable.ic_menu_common,
                mMOSFragment));
        items.add(new MenuItem(R.string.menu_option, R.drawable.ic_menu_common, mOptionFragment));

        mMenuList = (ListView) findViewById(R.id.menu_content_lv);
        mMenuAdapter = new MenuAdapter(this, items);
        mMenuList.setAdapter(mMenuAdapter);

        mMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                LogHelper.d("OnItemClickListener");
                MenuItem posItem = (MenuItem) mMenuAdapter.getItem(position);
                if (posItem != null && posItem.mRelationFragment != null) {
                    setMainFragment(posItem.mRelationFragment);
                    mDragLayout.close();
                }
            }
        });
    }

    private void initMainFragment() {
        mCLFragment = new CommonKnowledgeFragment();
        mMNFragment = new MapNavigateFragment();
        mUTFragment = new UtilityToolFragment();
        mMOSFragment = new MyOutdoorSportFragment();
        mOptionFragment = new OptionFragment();

        setMainFragment(mCLFragment);
    }

    private void setMainFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_fl, fragment);
        transaction.commit();
    }
}
