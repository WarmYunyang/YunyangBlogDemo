package com.xiangrong.yunyang.dataconversion;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * 作者    yunyang
 * 时间    2019/1/2 11:27
 * 文件    DataConversion
 * 描述   全局Application
 */
public class App extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        LitePal.initialize(this);
    }

    public static Context getContext() {
        return sContext;
    }

}
