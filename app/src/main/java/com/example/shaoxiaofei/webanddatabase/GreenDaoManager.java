package com.example.shaoxiaofei.webanddatabase;

import android.content.Context;

import com.example.shaoxiaofei.webanddatabase.gen.DaoMaster;
import com.example.shaoxiaofei.webanddatabase.gen.DaoSession;

/**
 * Created by shaoxiaofei on 28/12/2017.
 */

public class GreenDaoManager
{
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private GreenDaoManager(){
    }

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder
    {
        private static final GreenDaoManager INSTANCE = new GreenDaoManager();
    }

    /**
     * 对外唯一实例的接口
     *
     * @return
     */
    public static GreenDaoManager getInstance()
    {
        return SingleInstanceHolder.INSTANCE;
    }

    /**
     * 初始化数据
     */
    public void init(Context context)
    {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context,
                "timBase");
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoMaster getmDaoMaster()
    {
        return mDaoMaster;
    }
    public DaoSession getmDaoSession()
    {
        return mDaoSession;
    }
    public DaoSession getNewSession()
    {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
