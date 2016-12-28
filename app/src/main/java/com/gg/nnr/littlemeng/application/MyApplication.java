package com.gg.nnr.littlemeng.application;

import android.app.Application;
import android.content.Context;

import com.gg.nnr.littlemeng.ui.MainActivity;
import com.xiaochao.lcrapiddeveloplibrary.Exception.core.Recovery;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;

        //初始化异常管理工具
//        Recovery.getInstance()
//                .debug(true)//关闭后 在错误统一管理页面不显示异常数据
//                .recoverInBackground(false)
//                .recoverStack(true)
//                .mainPage(MainActivity.class)//恢复页面
//                .init(this);
    }

    public static Context getInstance() {
        return instance;
    }
}
