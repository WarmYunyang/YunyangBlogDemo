package com.yunyang.slidingintroscreendemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    @SuppressWarnings("ConstantConditions")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 当按下时，允许介绍页_导航页再次显示
        findViewById(R.id.clear_shared_prefs_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowIntroductionToShowAgain();
            }
        });
    }

    /**
     * 清除标志，该标志防止介绍显示两次。
     */
    private void allowIntroductionToShowAgain() {
        final SharedPreferences sp = getSharedPreferences(GuideActivity.DISPLAY_ONCE_PREFS,
                MODE_PRIVATE);
        sp.edit().putBoolean(GuideActivity.DISPLAY_ONCE_KEY, false).apply();
        Toast.makeText(this, "清除成功", Toast.LENGTH_SHORT).show();
    }

}
