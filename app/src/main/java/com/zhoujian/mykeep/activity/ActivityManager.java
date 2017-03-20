package com.zhoujian.mykeep.activity;

import android.content.Context;

/**
 * Created by zhoujian on 2017/3/18.
 */

public class ActivityManager
{
    private Context mContext;
    private static ActivityManager manager;
    private ActivityManager(Context mContext)
    {
        this.mContext = mContext.getApplicationContext();
    }
    public static ActivityManager getInstance(Context mContext)
    {
        if (manager!=null)
        {
            manager = new ActivityManager(mContext);
        }
        return manager;
    }
}
