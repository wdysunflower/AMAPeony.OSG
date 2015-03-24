package com.peony.osg.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.peony.osg.R;
import com.peony.osg.model.manager.UserManager;
import com.peony.osg.model.object.MenuItem;
import com.peony.osg.view.mainframe.*;
import com.peony.osg.view.widget.menu.ResideMenu;
import com.peony.osg.view.widget.menu.ResideMenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdynetposa on 2014/11/26.
 */
public class MainActivity extends FragmentActivity {

    private static ResideMenu resideMenu;
    private TextView mActionTitle;

    public static ResideMenu getMainResideMenu() {
        return resideMenu;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initMenuView();
        initListener();
    }

    private void initView() {
        mActionTitle = (TextView) findViewById(R.id.action_bar_title);
    }

    private void initMenuView() {
        // 构造菜单控件
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_main_bg);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.6f);
        resideMenu.setBGClick(true);

        // 左侧自定义菜单
        MenuItem mineItem =
                new MenuItem(R.string.menu_mine, R.drawable.user_default_icon, MineFragment.class);

        View mineMenu = getLayoutInflater().inflate(R.layout.menu_mine_item, null);
        mineMenu.setOnClickListener(menuItemOnClick);
        mineMenu.setTag(mineItem);
        resideMenu.setMainMenu(mineMenu);

        // 左侧列表菜单
        List<MenuItem> leftMenus = new ArrayList<MenuItem>();
        leftMenus.add(new MenuItem(R.string.menu_mine_sport, R.drawable.menu_mine_sport_icon,
                SportFragment.class));
        leftMenus.add(new MenuItem(R.string.menu_mine_course, R.drawable.menu_mine_course_icon,
                TeachFragment.class));
        leftMenus.add(new MenuItem(R.string.menu_mine_road, R.drawable.menu_mine_road_icon,
                MapFragment.class));
        leftMenus.add(new MenuItem(R.string.menu_mine_tool, R.drawable.menu_mine_tool_icon,
                MapFragment.class));
        leftMenus.add(new MenuItem(-1, R.drawable.menu_mine_calendar_icon, MapFragment.class));

        for (int i = 0; i < leftMenus.size(); i++) {
            MenuItem menuItem = leftMenus.get(i);
            ResideMenuItem item = new ResideMenuItem(this, menuItem.mIconRes, menuItem.mTitleRes);
            item.setOnClickListener(menuItemOnClick);
            item.setTag(menuItem);
            resideMenu.addMenuItem(item, ResideMenu.DIRECTION_LEFT);
        }

        // 右侧列表菜单
        List<MenuItem> rightMenus = new ArrayList<MenuItem>();
        rightMenus.add(new MenuItem(R.string.menu_sport, R.drawable.menu_sport_icon,
                SportFragment.class));
        rightMenus.add(new MenuItem(R.string.menu_teach, R.drawable.menu_teach_icon,
                TeachFragment.class));
        rightMenus
                .add(new MenuItem(R.string.menu_map, R.drawable.menu_map_icon, MapFragment.class));

        for (int i = 0; i < rightMenus.size(); i++) {
            MenuItem menuItem = rightMenus.get(i);
            ResideMenuItem item = new ResideMenuItem(this, menuItem.mIconRes, menuItem.mTitleRes);
            item.setOnClickListener(menuItemOnClick);
            item.setTag(menuItem);
            resideMenu.addMenuItem(item, ResideMenu.DIRECTION_RIGHT);
        }

        // 设置当前视图
        setMainView(mineItem);
    }

    private void initListener() {
        // 顶部工具栏
        if (UserManager.isLogin()) {
            findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
                }
            });
        } else {
            Button leftBtn = (Button) findViewById(R.id.title_bar_left_menu);
            leftBtn.setBackgroundResource(R.drawable.user_login_on_title_icon);
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

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

        Fragment fragment = MainFragmentManager.getFragment(menuItem.relationData);
        if (fragment != null) {
            transaction.replace(R.id.main_fl, fragment);
            transaction.commit();
        }
    }
}
