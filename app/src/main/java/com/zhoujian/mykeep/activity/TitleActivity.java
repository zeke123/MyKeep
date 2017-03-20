package com.zhoujian.mykeep.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhoujian.mykeep.R;

/**
 * Created by zhoujian on 2017/3/17.
 */

public class TitleActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_title6);

        //((ViewStub) findViewById(R.id.stub)).setVisibility(View.VISIBLE);
       // View importPanel = ((ViewStub) findViewById(R.id.stub)).inflate();
    }

}
