package com.zhoujian.mykeep.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.zhoujian.mykeep.R;
import com.zhoujian.mykeep.view.CircleProgressbar;

public class SplashActivity extends AppCompatActivity
{


    private static final String TAG ="SplashActivity";

    private CircleProgressbar mCircleProgressbar;

    private boolean isClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        mCircleProgressbar = (CircleProgressbar) findViewById(R.id.tv_red_skip);
        mCircleProgressbar.setOutLineColor(Color.TRANSPARENT);
        mCircleProgressbar.setInCircleColor(Color.parseColor("#505559"));
        mCircleProgressbar.setProgressColor(Color.parseColor("#1BB079"));
        mCircleProgressbar.setProgressLineWidth(5);
        mCircleProgressbar.setProgressType(CircleProgressbar.ProgressType.COUNT);
        mCircleProgressbar.setTimeMillis(5000);
        mCircleProgressbar.reStart();

        mCircleProgressbar.setCountdownProgressListener(1,progressListener);

        mCircleProgressbar.setOnClickListener(new View.OnClickListener() {
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
        public void onProgress(int what, int progress)
        {

            if(what==1 && progress==100 && !isClick)
            {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                Log.e(TAG, "onProgress: =="+progress );
            }

        }
    };

}
