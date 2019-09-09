package com.xiangrong.yunyang.indexscanicon.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者    yunyang
 * 时间    2019/3/13 10:48
 * 文件    YunyangBlogDemo
 * 描述   主页面的Fragment适配器
 */
public class FragmentIndexAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public FragmentIndexAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public Fragment getItem(int fragment) {
        return fragments.get(fragment);
    }

    public int getCount() {
        return fragments.size();
    }

}
