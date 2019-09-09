package com.yunyang.yunyangblogdemo.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yunyang.yunyangblogdemo.fragment.GuideFragment;

/**
 * 作者    yunyang
 * 时间    2018/11/13 11:20
 * 文件    YunyangBlogDemo
 * 描述   引导页ViewPager的适配器
 */
public class GuidePagerAdapter extends FragmentPagerAdapter {

    public GuidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GuideFragment.newInstance(position + 1);

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "PAGE ONE";
            case 1:
                return "PAGE TWO";
            case 2:
                return "PAGE THREE";
        }
        return null;
    }

}
