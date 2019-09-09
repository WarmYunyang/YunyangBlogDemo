package com.xiangrong.yunyang.dataconversion.view.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

/**
 * 作者    yunyang
 * 时间    2019/1/2 16:12
 * 文件    DataConversion
 * 描述   加载Dialog
 */
public class LoadingDialog extends ProgressDialog {
    public LoadingDialog(Context context, String str) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setProgressStyle(STYLE_SPINNER);
        setMessage(str);
    }
}
