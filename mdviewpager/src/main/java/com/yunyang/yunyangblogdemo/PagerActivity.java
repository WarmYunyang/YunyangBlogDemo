package com.yunyang.yunyangblogdemo;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.yunyang.yunyangblogdemo.adapter.GuidePagerAdapter;
import com.yunyang.yunyangblogdemo.utils.Utils;

public class PagerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "yunyang";

    private ViewPager mViewPager;
    private Button btn_skip;
    private ImageView image_indicator_zero;
    private ImageView image_indicator_one;
    private ImageView image_indicator_two;
    private Button btn_finish;
    private ImageButton mNextBtn;

    private GuidePagerAdapter mGuidePagerAdapter;

    private int lastLeftValue = 0;
    private ImageView[] indicators;
    // 当前页面位置
    private int currentPage = 0;

    // 是否首次进入引导页的标记
    private boolean isFirstGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 沉浸式状态栏
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            // 更改状态栏颜色
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black_trans80));
        }
        setContentView(R.layout.activity_pager);
        // 首次进入App，展示引导页。
        isFirstGuide = Utils.readSharedSetting(PagerActivity.this, "isGuide", false);
        if (isFirstGuide) {
            gotoMain();
        }
        initView();
        initMDViewPager();
    }

    /**
     * 跳转首页
     */
    private void gotoMain() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
         /*
             注意：
            1、overridePendingTransition方法必须在startActivity()或者finish()方法的后面。
            2、如果参数是0，表示没有动画
            public void overridePendingTransition(int enterAnim, int exitAnim) {}
            在A启动B时：
            enterAnim：是B进入的动画
            exitAnim：是A退出的动画
             */
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void initMDViewPager() {
        mGuidePagerAdapter = new GuidePagerAdapter(getSupportFragmentManager());
        mNextBtn = (ImageButton) findViewById(R.id.btn_next);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
            mNextBtn.setImageDrawable(
                    Utils.tintMyDrawable(ContextCompat.getDrawable(this, R.drawable.ic_chevron_right_24dp), Color.WHITE)
            );
        indicators = new ImageView[]{
                image_indicator_zero,
                image_indicator_one,
                image_indicator_two
        };
        mViewPager.setAdapter(mGuidePagerAdapter);
        mViewPager.setCurrentItem(currentPage);
        updateIndicators(currentPage);

        final int color1 = ContextCompat.getColor(this, R.color.cyan);
        final int color2 = ContextCompat.getColor(this, R.color.green);
        final int color3 = ContextCompat.getColor(this, R.color.orange);

        final int[] colorList = new int[]{color1, color2, color3};
        // 使用ArgbEvaluator用于更新颜色，在前一个页面和后一个页面的颜色之间进行过渡。
        final ArgbEvaluator evaluator = new ArgbEvaluator();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 更新颜色
                int colorUpdate =
                        (Integer) evaluator.evaluate(
                                positionOffset,
                                colorList[position],
                                colorList[position == 2 ? position : position + 1]);
                mViewPager.setBackgroundColor(colorUpdate);
            }

            @Override
            public void onPageSelected(int position) {
                // 更新当前角标位置
                currentPage = position;
                updateIndicators(currentPage);

                switch (position) {
                    case 0:
                        mViewPager.setBackgroundColor(color1);
                        break;
                    case 1:
                        mViewPager.setBackgroundColor(color2);
                        break;
                    case 2:
                        mViewPager.setBackgroundColor(color3);
                        break;
                }

                btn_skip.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
                btn_finish.setVisibility(position == 2 ? View.VISIBLE : View.GONE);
                mNextBtn.setVisibility(position == 2 ? View.GONE : View.VISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    /**
     * 通过切换两个不同的drawable来更新指示器。
     */
    private void updateIndicators(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.indicator_selected : R.drawable.indicator_unselected
            );
        }
    }


    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.container);
        btn_skip = (Button) findViewById(R.id.btn_skip);
        image_indicator_zero = (ImageView) findViewById(R.id.image_indicator_zero);
        image_indicator_one = (ImageView) findViewById(R.id.image_indicator_one);
        image_indicator_two = (ImageView) findViewById(R.id.image_indicator_two);
        btn_finish = (Button) findViewById(R.id.btn_finish);
        mNextBtn = (ImageButton) findViewById(R.id.btn_next);

        btn_skip.setOnClickListener(this);
        btn_finish.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_skip:
                gotoMain();
                break;
            case R.id.btn_finish:
                Utils.saveSharedSetting(PagerActivity.this, "isGuide", true);
                gotoMain();
                break;
            case R.id.btn_next:
                currentPage += 1;
                if (currentPage > 2) {
                    currentPage = 2;
                }
                mViewPager.setCurrentItem(currentPage, true);
                break;
            default:
                break;
        }
    }
}
