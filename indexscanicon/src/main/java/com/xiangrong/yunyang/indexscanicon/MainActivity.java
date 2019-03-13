package com.xiangrong.yunyang.indexscanicon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xiangrong.yunyang.indexscanicon.adapter.FragmentIndexAdapter;
import com.xiangrong.yunyang.indexscanicon.customview.MyViewPager;
import com.xiangrong.yunyang.indexscanicon.fragment.ExampleFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyViewPager index_vp_fragment_list_top;
    private ImageView index_bottom_bar_home_image;
    private LinearLayout index_bottom_bar_home;
    private ImageView index_bottom_bar_dynamic_state_image;
    private LinearLayout index_bottom_bar_dynamic_state;
    private ImageView index_bottom_bar_integral_image;
    private LinearLayout index_bottom_bar_integral;
    private ImageView index_bottom_bar_me_image;
    private LinearLayout index_bottom_bar_me;
    private FrameLayout index_fl_bottom_bar;
    private ImageView index_bottom_bar_scan;
    private RelativeLayout index_rl_contain;

    private List<Fragment> mFragments;

    private FragmentIndexAdapter mFragmentIndexAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        index_bottom_bar_home.setOnClickListener(new TabOnClickListener(0));
        index_bottom_bar_dynamic_state.setOnClickListener(new TabOnClickListener(1));
        index_bottom_bar_integral.setOnClickListener(new TabOnClickListener(2));
        index_bottom_bar_me.setOnClickListener(new TabOnClickListener(3));
        index_bottom_bar_scan.setOnClickListener(new TabOnClickListener(4));
    }

    private void initIndexFragmentAdapter() {
        mFragmentIndexAdapter = new FragmentIndexAdapter(this.getSupportFragmentManager(), mFragments);
        index_vp_fragment_list_top.setAdapter(mFragmentIndexAdapter);
        index_bottom_bar_home.setSelected(true);
        index_vp_fragment_list_top.setCurrentItem(0);
        index_vp_fragment_list_top.setOffscreenPageLimit(3);
        index_vp_fragment_list_top.addOnPageChangeListener(new TabOnPageChangeListener());
    }

    private void initData() {
        mFragments = new ArrayList<Fragment>();
        mFragments.add(ExampleFragment.newInstance(getResources().getString(R.string.index_bottom_bar_home)));
        mFragments.add(ExampleFragment.newInstance(getResources().getString(R.string.index_bottom_bar_dynamic_state)));
        mFragments.add(ExampleFragment.newInstance(getResources().getString(R.string.index_bottom_bar_integral)));
        mFragments.add(ExampleFragment.newInstance(getResources().getString(R.string.index_bottom_bar_me)));
        initIndexFragmentAdapter();
    }

    private void initView() {
        index_vp_fragment_list_top = (MyViewPager) findViewById(R.id.index_vp_fragment_list_top);
        index_bottom_bar_home_image = (ImageView) findViewById(R.id.index_bottom_bar_home_image);
        index_bottom_bar_home = (LinearLayout) findViewById(R.id.index_bottom_bar_home);
        index_bottom_bar_dynamic_state_image = (ImageView) findViewById(R.id.index_bottom_bar_dynamic_state_image);
        index_bottom_bar_dynamic_state = (LinearLayout) findViewById(R.id.index_bottom_bar_dynamic_state);
        index_bottom_bar_integral_image = (ImageView) findViewById(R.id.index_bottom_bar_integral_image);
        index_bottom_bar_integral = (LinearLayout) findViewById(R.id.index_bottom_bar_integral);
        index_bottom_bar_me_image = (ImageView) findViewById(R.id.index_bottom_bar_me_image);
        index_bottom_bar_me = (LinearLayout) findViewById(R.id.index_bottom_bar_me);
        index_fl_bottom_bar = (FrameLayout) findViewById(R.id.index_fl_bottom_bar);
        index_bottom_bar_scan = (ImageView) findViewById(R.id.index_bottom_bar_scan);
        index_rl_contain = (RelativeLayout) findViewById(R.id.index_rl_contain);
    }

    /**
     * Bottom_Bar的点击事件
     */
    public class TabOnClickListener implements View.OnClickListener {

        private int index = 0;

        public TabOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            if (index == 4) {
                // 跳转到Scan界面
                Toast.makeText(MainActivity.this, "点击了扫描按钮", Toast.LENGTH_SHORT).show();
            } else {
                //选择某一页
                index_vp_fragment_list_top.setCurrentItem(index, false);
            }
        }

    }

    public class TabOnPageChangeListener implements ViewPager.OnPageChangeListener {

        //当滑动状态改变时调用
        public void onPageScrollStateChanged(int state) {
        }

        //当前页面被滑动时调用
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        //当新的页面被选中时调用
        public void onPageSelected(int position) {
            resetTextView();
            switch (position) {
                case 0:
                    index_bottom_bar_home.setSelected(true);
                    break;
                case 1:
                    index_bottom_bar_dynamic_state.setSelected(true);
                    break;
                case 2:
                    index_bottom_bar_integral.setSelected(true);
                    break;
                case 3:
                    index_bottom_bar_me.setSelected(true);
                    break;
            }
        }
    }

    /**
     * 重置所有TextView的字体颜色
     */
    private void resetTextView() {
        index_bottom_bar_home.setSelected(false);
        index_bottom_bar_dynamic_state.setSelected(false);
        index_bottom_bar_integral.setSelected(false);
        index_bottom_bar_me.setSelected(false);
    }

}
