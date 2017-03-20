package com.zhoujian.mykeep.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zhoujian.mykeep.R;
import com.zhoujian.mykeep.activity.TitleActivity;


public class FourthFragment extends Fragment {

     public static Fragment newInstance()
     {
         FourthFragment fragment = new FourthFragment();
         return fragment;
     }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.four_fragment_layout,null);

        initView(view);
        return view;
    }


    private void initView(View view)
    {
        Button button = (Button)view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                startActivity(new Intent(getActivity(), TitleActivity.class));
            }
        });
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
