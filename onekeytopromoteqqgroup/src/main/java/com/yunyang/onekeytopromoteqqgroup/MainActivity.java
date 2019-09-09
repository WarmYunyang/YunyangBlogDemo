package com.yunyang.onekeytopromoteqqgroup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 一键推广QQ群
    public void oneKeyToPromoteQQGroup(View view) {
        if (joinQQGroup("dTu626C84_SY5RsO4JrK6ssk_ea-XWs4")) {
            Toast.makeText(this, "呼起手Q成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "呼起失败\n未安装手Q或安装的版本不支持", Toast.LENGTH_SHORT).show();
        }
    }

    /****************
     *
     * 发起添加群流程。群号：IT坚持之路(935527649) 的 key 为： dTu626C84_SY5RsO4JrK6ssk_ea-XWs4
     * 调用 joinQQGroup(dTu626C84_SY5RsO4JrK6ssk_ea-XWs4) 即可发起手Q客户端申请加群 IT坚持之路(935527649)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
     ******************/
    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面
        // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }

}
