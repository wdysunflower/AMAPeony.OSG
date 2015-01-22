package com.peony.osg.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import com.peony.osg.R;
import com.peony.osg.model.object.MenuItem;
import com.peony.osg.view.fragment.*;
import com.peony.osg.view.widget.menu.ResideMenu;
import com.peony.osg.view.widget.menu.ResideMenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdynetposa on 2014/11/26.
 */
public class MainActivity extends Activity {

    private static ResideMenu resideMenu;
    private TextView mActionTitle;

    private Fragment mMineFragment;
    private Fragment mSportFragment;
    private Fragment mTeachFragment;
    private Fragment mMapFragment;

    public static ResideMenu getMainResideMenu() {
        return resideMenu;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initMainFragment();
        initMenuListView();
    }

    private void initView() {
        mActionTitle = (TextView) findViewById(R.id.action_bar_title);
    }

    private void initMainFragment() {
        mMineFragment = new MineFragment();
        mSportFragment = new SportFragment();
        mTeachFragment = new TeachFragment();
        mMapFragment = new MapFragment();
    }

    private void initMenuListView() {
        // 构造菜单数据
        List<MenuItem> items = new ArrayList<MenuItem>();
        MenuItem mineItem =
                new MenuItem(R.string.menu_mine, R.drawable.user_default_icon, mMineFragment);

        items.add(new MenuItem(R.string.menu_sport, R.drawable.menu_activity_icon, mSportFragment));
        items.add(new MenuItem(R.string.menu_teach, R.drawable.menu_teach_icon, mTeachFragment));
        items.add(new MenuItem(R.string.menu_map, R.drawable.menu_map_icon, mMapFragment));

        // 构造菜单控件
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_main_bg);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.6f);
        resideMenu.setBGClick(true);

        View mineMenu = getLayoutInflater().inflate(R.layout.menu_mine_item, null);
        mineMenu.setOnClickListener(menuItemOnClick);
        mineMenu.setTag(mineItem);
        resideMenu.setMainMenu(mineMenu);

        for (int i = 0; i < items.size(); i++) {
            MenuItem menuItem = items.get(i);
            ResideMenuItem item = new ResideMenuItem(this, menuItem.mIconRes, menuItem.mTitleRes);
            item.setOnClickListener(menuItemOnClick);
            item.setTag(menuItem);
            resideMenu.addMenuItem(item, ResideMenu.DIRECTION_RIGHT);
        }

        setMainView(mineItem);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    private View.OnClickListener menuItemOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MenuItem menuItem = (MenuItem) view.getTag();
            if (menuItem != null) {
                setMainView(menuItem);
                resideMenu.closeMenu();
            }
        }
    };

    private void setMainView(MenuItem menuItem) {
        mActionTitle.setText(menuItem.mTitleRes);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_fl, menuItem.mRelationFragment);
        transaction.commit();
    }
}
