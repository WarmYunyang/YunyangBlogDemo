package com.yunyang.yunyangblogdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;

/**
 * 作者    yunyang
 * 时间    2018/11/13 11:39
 * 文件    YunyangBlogDemo
 * 描述   工具类
 */
public class Utils {

    private static final String PREFERENCES_FILE = "sp_settings";

    public static boolean readSharedSetting(Context context, String settingSPName, boolean defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(settingSPName, defaultValue);
    }

    public static void saveSharedSetting(Context context, String settingSPName, boolean settingValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(settingSPName, settingValue);
        editor.apply();
    }

    public static Drawable tintMyDrawable(Drawable drawable, int color) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, color);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

}
