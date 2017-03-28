
### 在上篇博客[ CoordinatorLayout、AppBarLayout实现上滑隐藏图片，下滑显示图片](http://blog.csdn.net/u014005316/article/details/62048116)的基础上，仿掘金app首界面，向上滑动隐藏FloatingActionButton，向下滑动显示FloatingActionButton的效果。


layout_fab_button.xml

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/app_bar_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <android.support.design.widget.CollapsingToolbarLayout
            android:id="@+id/collapsing_toolbar_layout"
            android:layout_width="match_parent"
            android:layout_height="240dp"
            app:contentScrim="@color/colorPrimary"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:scaleType="centerCrop"
                android:src="@mipmap/splash"
                app:layout_collapseMode="parallax"/>

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                app:layout_collapseMode="pin"
                app:navigationIcon="@mipmap/back"
                app:title="keep"
                app:titleTextColor="@color/white"/>

        </android.support.design.widget.CollapsingToolbarLayout>

    </android.support.design.widget.AppBarLayout>


    <android.support.v7.widget.RecyclerView

        android:id="@+id/id_recyclerview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"/>


    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="15dp"
        android:layout_marginRight="15dp"
        android:src="@mipmap/ic_book_list"
        app:elevation="4dp"
        app:layout_anchor="@+id/id_recyclerview"
        app:layout_anchorGravity="right|bottom"
        app:layout_behavior="com.zhoujian.mykeep.behavior.BehaviorDefault"/>

</android.support.design.widget.CoordinatorLayout>
```


BehaviorDefault.java

```
package com.zhoujian.mykeep.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhoujian on 2017/3/15.
 */


public class BehaviorDefault extends FloatingActionButton.Behavior {

    public BehaviorDefault(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                                       final View directTargetChild, final View target, final int nestedScrollAxes)
    {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                               final View target, final int dxConsumed, final int dyConsumed,
                               final int dxUnconsumed, final int dyUnconsumed)
    {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE)
        {
            child.hide();
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE)
        {
            child.show();
        }
    }
}

```





FAButtonActivity.java

```
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

```


MainAdapter.java

```
package com.zhoujian.mykeep.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zhoujian.mykeep.R;
import com.zhoujian.mykeep.bean.Person;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder>
{

	private ArrayList<Person> mDatas;
	private LayoutInflater mInflater;
	private OnItemClickLitener mOnItemClickLitener;

	public interface OnItemClickLitener
	{
		void onItemClick(View view, int position);
		void onItemLongClick(View view, int position);
	}

	public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
	{
		this.mOnItemClickLitener = mOnItemClickLitener;
	}
	

	public MainAdapter(Context context, ArrayList<Person> datas)
	{
		mInflater = LayoutInflater.from(context);
		mDatas = datas;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		MyViewHolder holder = new MyViewHolder(mInflater.inflate(
				R.layout.item_home, parent, false));
		return holder;
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position)
	{
		holder.tv.setText(mDatas.get(position).getName());

		// 如果设置了回调，则设置点击事件
		if (mOnItemClickLitener != null)
		{
			holder.itemView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemClick(holder.itemView, pos);
				}
			});
			
			holder.itemView.setOnLongClickListener(new OnLongClickListener()
			{
				@Override
				public boolean onLongClick(View v)
				{
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
					removeData(pos);
					return false;
				}
			});
		}
	}

	@Override
	public int getItemCount()
	{
		return mDatas.size();
	}

	public void removeData(int position)
	{
		mDatas.remove(position);
		notifyItemRemoved(position);
	}

	class MyViewHolder extends ViewHolder
	{

		TextView tv;
		public MyViewHolder(View view)
		{
			super(view);
			tv = (TextView) view.findViewById(R.id.id_num);

		}
	}
}
```

### 实现效果

![第一张图片](https://user-gold-cdn.xitu.io/2017/3/15/40454d0f34d7dd156737f9841bfbb9ad)


![第二张图片](https://user-gold-cdn.xitu.io/2017/3/15/fa279807e8eca06749765046f38f7ddb)



### 源码下载

[源码下载：https://github.com/zeke123/MyKeep](https://github.com/zeke123/MyKeep)




