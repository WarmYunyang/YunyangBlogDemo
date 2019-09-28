package com.yunyang.guidepageslidingintro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.matthewtamlin.sliding_intro_screen_library.buttons.IntroButton;
import com.matthewtamlin.sliding_intro_screen_library.core.IntroActivity;
import com.matthewtamlin.sliding_intro_screen_library.core.LockableViewPager;
import com.yunyang.guidepageslidingintro.fragment.EmptyFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

public class GuideActivity extends IntroActivity {

    private float[] animationTimes = new float[]{0f, 0.3333f, 0.6666f, 1f, 1f};

    private LockableViewPager mLockableViewPager;

    private LottieAnimationView mLottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.NoActionBar);
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_guide, null);
        mLottieAnimationView = (LottieAnimationView) view.findViewById(R.id.animation_view);
        getRootView().addView(mLottieAnimationView, 0);
        mLockableViewPager = (LockableViewPager) findViewById(R.id.intro_activity_viewPager);
        hideStatusBar();
        setViewPagerScroller();
        initEvent();
    }

    private void initEvent() {

        mLockableViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setAnimationProgress(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setAnimationProgress(int position, float positionOffset) {
        float startProgress = animationTimes[position];
        float endProgress = animationTimes[position + 1];
        mLottieAnimationView.setProgress(Lerp(startProgress, endProgress, positionOffset));
    }

    /**
     * 可以用于物体到达另外一个目标物体之间进行平滑过渡运动效果
     *
     * @param start 开始位置
     * @param end   结束位置
     * @param t     [0,1]
     */
    private float Lerp(float start, float end, float t) {
        return start + t * (end - start);
    }

    private void setViewPagerScroller() {
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);
            Scroller mScroller = new Scroller(this, (Interpolator) interpolator.get(null)) {
                @Override
                public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                    super.startScroll(startX, startY, dx, dy, duration * 7);
                }
            };
            scrollerField.set(mLockableViewPager, mScroller);
        } catch (NoSuchFieldException e) {
            // Do nothing.
        } catch (IllegalAccessException e) {
            // Do nothing.
        }
    }

    @Override
    protected Collection<? extends Fragment> generatePages(Bundle savedInstanceState) {
        final ArrayList<Fragment> pages = new ArrayList<>();
        pages.add(EmptyFragment.newInstance());
        pages.add(EmptyFragment.newInstance());
        pages.add(EmptyFragment.newInstance());
        pages.add(EmptyFragment.newInstance());
        return pages;
    }

    @Override
    protected IntroButton.Behaviour generateFinalButtonBehaviour() {
        return new IntroButton.Behaviour() {
            @Override
            public void setActivity(IntroActivity activity) {
                finish();
            }

            @Override
            public IntroActivity getActivity() {
                return null;
            }

            @Override
            public void run() {

            }
        };
    }

}
