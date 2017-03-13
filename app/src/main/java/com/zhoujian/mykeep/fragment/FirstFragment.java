package com.zhoujian.mykeep.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhoujian.mykeep.adapter.MainAdapter;
import com.zhoujian.mykeep.R;
import com.zhoujian.mykeep.bean.Person;

import java.util.ArrayList;



public class FirstFragment extends Fragment {


    private RecyclerView mRecyclerView;

    private ArrayList<Person> personList;
    private MainAdapter adapter;


    public static Fragment newInstance(){
         FirstFragment fragment = new FirstFragment();
         return fragment;
     }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment_layout,null);

        initData();
        initView(view);

        initEvent();

        return view;
    }

    private void initEvent()
    {
        adapter.setOnItemClickLitener(new MainAdapter.OnItemClickLitener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(getActivity(),
                        personList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(getActivity(),
                        personList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(View view)
    {

        mRecyclerView =(RecyclerView) view.findViewById(R.id.id_recyclerview);
        adapter = new MainAdapter(getActivity(), personList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

    }

    protected void initData()
    {
        personList = new ArrayList<Person>();
        Person mPerson0= new Person("宋江");
        personList.add(mPerson0);
        Person mPerson1= new Person("卢俊义");
        personList.add(mPerson1);
        Person mPerson2= new Person("吴用");
        personList.add(mPerson2);
        Person mPerson3= new Person("公孙胜");
        personList.add(mPerson3);
        Person mPerson4= new Person("关胜");
        personList.add(mPerson4);
        Person mPerson5= new Person("林冲");
        personList.add(mPerson5);
        Person mPerson6= new Person("秦明");
        personList.add(mPerson6);
        Person mPerson7= new Person("呼延灼");
        personList.add(mPerson7);
        Person mPerson8= new Person("花荣");
        personList.add(mPerson8);
        Person mPerson9= new Person("柴进");
        personList.add(mPerson9);
        Person mPerson10= new Person("李应");
        personList.add(mPerson10);
        Person mPerson11= new Person("鲁智深");
        personList.add(mPerson11);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
