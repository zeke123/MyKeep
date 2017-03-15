package com.zhoujian.mykeep.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.zhoujian.mykeep.R;
import com.zhoujian.mykeep.adapter.MainAdapter;
import com.zhoujian.mykeep.bean.Person;

import java.util.ArrayList;

/**
 * Created by zhoujian on 2017/3/15.
 */

public class FAButtonActivity extends AppCompatActivity {


    public static final String TAG = "ModeActivity";
    private RecyclerView mRecyclerView;
    private ArrayList<Person> personList;
    private MainAdapter adapter;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_fab_button);
        initData();
        initView();
    }


    private void initData()
    {
        personList = new ArrayList<Person>();
        Person mPerson0= new Person("林黛玉");
        personList.add(mPerson0);
        Person mPerson1= new Person("薛宝钗");
        personList.add(mPerson1);
        Person mPerson2= new Person("贾元春");
        personList.add(mPerson2);
        Person mPerson3= new Person("贾迎春");
        personList.add(mPerson3);
        Person mPerson4= new Person("贾探春");
        personList.add(mPerson4);
        Person mPerson5= new Person("贾惜春");
        personList.add(mPerson5);
        Person mPerson6= new Person("史湘云");
        personList.add(mPerson6);
        Person mPerson7= new Person("王熙凤");
        personList.add(mPerson7);
        Person mPerson8= new Person("秦可卿");
        personList.add(mPerson8);
        Person mPerson9= new Person("贾宝玉");
        personList.add(mPerson9);

    }

    private void initView()
    {
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.TRANSPARENT);
        toolbar.inflateMenu(R.menu.menu_search);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_search:
                        //点击设置
                        Toast.makeText(FAButtonActivity.this, "按钮被点击了", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });



        AppBarLayout app_bar_layout = (AppBarLayout)findViewById(R.id.app_bar_layout);

        final CollapsingToolbarLayout collapsing_toolbar_layout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_layout);
        collapsing_toolbar_layout.setTitle("");
        collapsing_toolbar_layout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsing_toolbar_layout.setExpandedTitleColor(getResources().getColor(R.color.white));
        collapsing_toolbar_layout.setExpandedTitleColor(Color.TRANSPARENT);


        app_bar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e(TAG,"appBarLayoutHeight:"+appBarLayout.getHeight()+" getTotalScrollRange:"+appBarLayout.getTotalScrollRange()+" offSet:"+verticalOffset);
                if(Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()){
                    toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                    collapsing_toolbar_layout.setTitle("keep");
                }else{
                    collapsing_toolbar_layout.setTitle("");
                }
            }
        });




        FloatingActionButton fab= (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(FAButtonActivity.this, "FloatingActionButton被点击了", Toast.LENGTH_SHORT).show();

            }
        });



        mRecyclerView =(RecyclerView) findViewById(R.id.id_recyclerview);
        adapter = new MainAdapter(FAButtonActivity.this, personList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(FAButtonActivity.this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(FAButtonActivity.this,DividerItemDecoration.VERTICAL));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

    }






}
