package com.example.cleverboy.news;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import fragment.MainFragment1;
import fragment.MainFragment2;
import fragment.MainFragment3;
import fragment.MainFragment4;
import fragment.MainFragment5;

public class MainActivity extends BaseActivity {


    private RadioGroup radioGroup;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.rg_01);
        initViewPager();
        initNavigationView();
       initToolBar();
        initActionBarDrawerToggle();
    }
    private ActionBarDrawerToggle toggle;
    private void initActionBarDrawerToggle() {
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, mToolBar, 0, 0);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();     // 同步drawerLayout和toolbar的状态
    }

    /**
     * 初始化标题栏
     */
    private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolBar);
        mToolBar.setTitle("广交院实训");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_02) {
            showToast("menu_02");
        }
        return super.onOptionsItemSelected(item);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment1());
        fragments.add(new MainFragment2());
        fragments.add(new MainFragment3());
        fragments.add(new MainFragment4());
        fragments.add(new MainFragment5());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    @Override
    public void initListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.rb_01:    // 点击单选时，切换ViewPager子界面
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_02:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_03:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_04:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.rb_05:
                        viewPager.setCurrentItem(4);
                        break;
                }
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position) {  // 当ViewPager子界面切换后，选中某个RadioButton
                    case 0:
                        radioGroup.check(R.id.rb_01);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_02);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb_03);
                        break;
                    case 3:
                        radioGroup.check(R.id.rb_04);
                        break;
                    case 4:
                        radioGroup.check(R.id.rb_05);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {

    }
}
