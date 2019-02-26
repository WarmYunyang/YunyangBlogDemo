package com.maigu.yang.jiguangdemo;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class MainActivity extends AppCompatActivity {

    private static final int MSG_SET_ALIAS = 1001;
    private static final int MSG_SET_TAGS = 1002;

    Set<String> tags = new HashSet<String>();

    private static final String TAG = "小白阳";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedUtil.putInt(this, "userId", 1);

//        if (!"success".equals(SharedUtil.getString(this, "alias"))) {
//            // 调用 Handler 来异步设置别名，一般都是用userId来进行设置别名（唯一性）。
//            mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS,
//                    SharedUtil.getInt(this, "userId") + ""));
//            // 调用 Handler 来异步设置标签。
//            tags.add("vip");
//            mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, tags));
//        }

        // 调用 Handler 来异步设置别名，一般都是用userId来进行设置别名（唯一性）。
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS,
                SharedUtil.getInt(this, "userId") + ""));
        // 调用 Handler 来异步设置标签。
        tags.add("vip");
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, tags));

        if (JPushInterface.isPushStopped(App.getContext())) {
            JPushInterface.resumePush(App.getContext());
        }

    }

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAlias(getApplicationContext(),
                            (String) msg.obj,
                            mAliasCallback);
                    break;
                case MSG_SET_TAGS:
                    // 调用 JPush 接口来设置标签。
                    JPushInterface.setTags(getApplicationContext(),
                            tags,
                            mAliasCallback);
                    break;
                default:
                    break;
            }
        }
    };

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            switch (code) {
                case 0:
                    Log.e(TAG, "TagAliasCallback success");
                    //"Set tag and alias success";
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    SharedUtil.putString(MainActivity.this, "alias", "success");
                    break;
                case 6002:
                    // 延迟 60 秒来调用 Handler 设置别名，标签
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_TAGS, tags), 1000 * 60);
                    break;
                default:
                    break;
            }
        }
    };

}