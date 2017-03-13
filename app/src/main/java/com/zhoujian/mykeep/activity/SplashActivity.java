package com.zhoujian.mykeep.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zhoujian.mykeep.R;
import com.zhoujian.mykeep.view.CircleProgressbar;

public class SplashActivity extends AppCompatActivity
{


    private static final String TAG ="SplashActivity";


    private CircleProgressbar mTvSkip;

    private boolean isClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        mTvSkip = (CircleProgressbar) findViewById(R.id.tv_red_skip);
        mTvSkip.setOutLineColor(Color.TRANSPARENT);
        mTvSkip.setInCircleColor(Color.parseColor("#505559"));
        mTvSkip.setProgressColor(Color.parseColor("#1BB079"));
        mTvSkip.setProgressLineWidth(5);
        mTvSkip.setProgressType(CircleProgressbar.ProgressType.COUNT);
        mTvSkip.setTimeMillis(5000);
        mTvSkip.reStart();

        mTvSkip.setCountdownProgressListener(1,progressListener);

        mTvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                isClick = true;
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private CircleProgressbar.OnCountdownProgressListener progressListener = new CircleProgressbar.OnCountdownProgressListener() {
        @Override
        public void onProgress(int what, int progress) {

            if(what==1 && progress==100 && !isClick){

                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

                Log.e(TAG, "onProgress: =="+progress );
            }






        }
    };





    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_start) {

            mTvSkip.reStart();
        }
        return true;
    }
}
