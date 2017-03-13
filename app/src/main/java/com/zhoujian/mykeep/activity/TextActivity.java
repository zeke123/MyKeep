package com.zhoujian.mykeep.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.zhoujian.mykeep.R;
import com.zhoujian.mykeep.adapter.MyFragmentAdapter;
import com.zhoujian.mykeep.fragment.FirstFragment;
import com.zhoujian.mykeep.fragment.SecondFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhoujian on 2017/3/10.
 */

public class TextActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static final String[] sTitle = new String[]{"精选", "训练"};
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

       // mTabLayout.newTab().setCustomView(R.layout.ite)

        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[1]));


       // mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[2]));
       // mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[3]));

       // toolbar = (Toolbar) findViewById(R.id.toolbar);

       // toolbar.setTitle("");


        //设置 Toolbar menu
       // toolbar.inflateMenu(R.menu.menu_search);

        // 设置menu item 点击事件
      /*  toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_search:
                        //点击设置
                        Toast.makeText(TextActivity.this, "搜索按钮被点击了", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });*/


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(TAG, "onTabSelected:" + tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i(TAG, "onTabUnselected:" + tab.getText());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FirstFragment.newInstance());
        fragments.add(SecondFragment.newInstance());
        //fragments.add(ThirdFragment.newInstance());
       // fragments.add(FourthFragment.newInstance());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments, Arrays.asList(sTitle));
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "select page:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}



