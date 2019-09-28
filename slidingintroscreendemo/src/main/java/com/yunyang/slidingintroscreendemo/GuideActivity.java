package com.yunyang.slidingintroscreendemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.matthewtamlin.sliding_intro_screen_library.background.BackgroundManager;
import com.matthewtamlin.sliding_intro_screen_library.background.ColorBlender;
import com.matthewtamlin.sliding_intro_screen_library.buttons.IntroButton;
import com.matthewtamlin.sliding_intro_screen_library.core.IntroActivity;
import com.matthewtamlin.sliding_intro_screen_library.transformers.MultiViewParallaxTransformer;
import com.yunyang.slidingintroscreendemo.fragment.NumberFragment;

import java.util.ArrayList;
import java.util.Collection;

public class GuideActivity extends IntroActivity {

    /**
     * 用于混合背景的颜色:蓝色、粉色、紫色。
     */
    private static final int[] BACKGROUND_COLORS = {0xff304FFE, 0xffcc0066, 0xff9900ff};

    public static final String DISPLAY_ONCE_PREFS = "display_only_once_spfile";

    public static final String DISPLAY_ONCE_KEY = "display_only_once_spkey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 样式_标题栏
        setTheme(R.style.NoActionBar);
        super.onCreate(savedInstanceState);

        // 引导页_介绍页_只显示一次_当进入到App主页面时
        if (introductionCompletedPreviously()) {
            final Intent nextActivity = new Intent(this, MainActivity.class);
            startActivity(nextActivity);
        }

        // 隐藏状态栏
        hideStatusBar();
        configureTransformer();
        configureBackground();
    }

    /**
     * 此活动显示的页面
     */
    @Override
    protected Collection<Fragment> generatePages(Bundle savedInstanceState) {
        final ArrayList<Fragment> pages = new ArrayList<>();
        for (int i = 0; i < BACKGROUND_COLORS.length; i++) {
            final NumberFragment fragment = new NumberFragment();
            fragment.setNumber(i + 1);
            pages.add(fragment);
        }
        return pages;
    }

    /**
     * 按钮行为
     */
    @Override
    @SuppressLint("CommitPrefEdits")
    protected IntroButton.Behaviour generateFinalButtonBehaviour() {
        // 引导页_介绍页_只显示一次_当进入到App主页面时_标识
        final SharedPreferences sp = getSharedPreferences(DISPLAY_ONCE_PREFS, MODE_PRIVATE);
        final SharedPreferences.Editor pendingEdits = sp.edit().putBoolean(DISPLAY_ONCE_KEY, true);

        // 定义跳转意图，并创建用于最终按钮的行为
        final Intent nextActivity = new Intent(this, MainActivity.class);
        return new IntroButton.ProgressToNextActivity(nextActivity, pendingEdits);
    }

    /**
     * 检查跳转意图标识
     */
    private boolean introductionCompletedPreviously() {
        final SharedPreferences sp = getSharedPreferences(DISPLAY_ONCE_PREFS, MODE_PRIVATE);
        return sp.getBoolean(DISPLAY_ONCE_KEY, false);
    }

    /**
     * 将此IntroActivity设置为使用multiviewparallel transformer页面转换器。
     */
    private void configureTransformer() {
        final MultiViewParallaxTransformer transformer = new MultiViewParallaxTransformer();
        transformer.withParallaxView(R.id.number_fragment_text_holder, 1.2f);
        setPageTransformer(false, transformer);
    }

    /**
     * 将此IntroActivity设置为使用ColorBlender后台管理器。
     */
    private void configureBackground() {
        final BackgroundManager backgroundManager = new ColorBlender(BACKGROUND_COLORS);
        setBackgroundManager(backgroundManager);
    }

}
