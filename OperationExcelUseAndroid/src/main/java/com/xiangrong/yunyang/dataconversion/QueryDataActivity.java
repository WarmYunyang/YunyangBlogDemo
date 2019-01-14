package com.xiangrong.yunyang.dataconversion;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.xiangrong.yunyang.dataconversion.adater.QueryDataAdapter;
import com.xiangrong.yunyang.dataconversion.entity.CurrentFileName;
import com.xiangrong.yunyang.dataconversion.entity.School;
import com.xiangrong.yunyang.dataconversion.fragment.FragmentDish;
import com.xiangrong.yunyang.dataconversion.view.dialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class QueryDataActivity extends AppCompatActivity {

    private static final String POSITION = "POSITION";

    private QueryDataAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    private List<Fragment> mFragments;
    private String[] mTitles;
    private int[] mInts;

    private String ownershipDataSheetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_data);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    private void initData() {
        findDbLitePal();
    }

    /**
     * 根据inventoryResults（盘点结果）去数据库中查找对应数据
     */
    private void findDbLitePal() {
        new AsyncTask<String, Void, Integer>() {
            @Override
            protected Integer doInBackground(String... params) {
                try {
                    if (LitePal.count(School.class) <= 0) {
                        Log.e("Excel", "LitePal数据库中无数据");
                        mTitles[0] = "全部";
                        mTitles[1] = "盘亏";
                        mTitles[2] = "无盈亏";
                        return 0;
                    } else {
                        // 全部
                        final int count = LitePal
                                .where("ownershipDataSheet = ?", ownershipDataSheetName)
                                .count(School.class);
                        // 减去行1
                        mInts[0] = count - 1;
                        // 盘亏
                        mInts[1] = LitePal
                                .where("inventoryResults = ? and ownershipDataSheet = ?", "盘亏", ownershipDataSheetName)
                                .count(School.class);
                        // 无盈亏
                        mInts[2] = LitePal
                                .where("inventoryResults = ? and ownershipDataSheet = ?", "无盈亏", ownershipDataSheetName)
                                .count(School.class);
                        mTitles[0] = "全部（" + mInts[0] + "）";
                        mTitles[1] = "盘亏（" + mInts[1] + "）";
                        mTitles[2] = "无盈亏（" + mInts[2] + "）";
                        return 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mTitles[0] = "全部";
                    mTitles[1] = "盘亏";
                    mTitles[2] = "无盈亏";
                    return 0;
                }
            }

            @Override
            protected void onPostExecute(Integer aVoid) {
                super.onPostExecute(aVoid);
                mFragments.add(FragmentDish.newInstance(0));
                mFragments.add(FragmentDish.newInstance(1));
                mFragments.add(FragmentDish.newInstance(2));
                if (mFragments.size() > 0 && mTitles.length > 0) {
                    mSectionsPagerAdapter = new QueryDataAdapter(getSupportFragmentManager(), mFragments, mTitles);
                    mViewPager.setAdapter(mSectionsPagerAdapter);
                    mViewPager.setOffscreenPageLimit(3);
                    mTabLayout.setupWithViewPager(mViewPager);
                    setupTabLayout(mTabLayout);
                }
            }
        }.execute();
    }

    private void setupTabLayout(TabLayout tabLayout) {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.container);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mFragments = new ArrayList<>();
        mTitles = new String[3];
        mInts = new int[3];
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, mTabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mViewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onCurrentFileNameEvent(CurrentFileName currentFile) {
        ownershipDataSheetName = currentFile.getFileName();
    }

}
