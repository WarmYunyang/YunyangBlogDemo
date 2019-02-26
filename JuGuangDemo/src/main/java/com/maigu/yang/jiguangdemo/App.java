package com.maigu.yang.jiguangdemo;

import android.app.Application;
import android.app.Notification;
import android.content.Context;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

public class App extends Application {

    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        JPushInterface.init(this);
        jpushSet();
    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        return applicationContext;
    }

    private void jpushSet() {
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.statusBarDrawable = R.mipmap.shenqi;
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(1, builder);

        /**
         * 设置通知栏样式 - 定义通知栏Layout
         */

        CustomPushNotificationBuilder builder1 =
                new CustomPushNotificationBuilder(this, R.layout.customer_notitfication_layout,
                        R.id.icon, R.id.title, R.id.text);
        builder1.layoutIconDrawable = R.mipmap.shenqi;
        builder.developerArg0 = "developerArg2";
        JPushInterface.setPushNotificationBuilder(2, builder1);
    }
}
