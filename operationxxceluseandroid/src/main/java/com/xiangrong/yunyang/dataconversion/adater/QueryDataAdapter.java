package com.xiangrong.yunyang.dataconversion.adater;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者    yunyang
 * 时间    2019/1/7 9:28
 * 文件    DataConversion
 * 描述   TabLayout的适配器
 */
public class QueryDataAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private String[] mTitles;

    public QueryDataAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles) {
        super(fm);
        mFragments = fragmentList;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

}
