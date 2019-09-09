package com.xiangrong.yunyang.dataconversion.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiangrong.yunyang.dataconversion.R;
import com.xiangrong.yunyang.dataconversion.adater.DishFragmentAdapter;
import com.xiangrong.yunyang.dataconversion.entity.CurrentFileName;
import com.xiangrong.yunyang.dataconversion.entity.School;
import com.xiangrong.yunyang.dataconversion.utils.StrUtil;
import com.xiangrong.yunyang.dataconversion.view.dialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者    yunyang
 * 时间    2019/1/7 9:38
 * 文件    DataConversion
 * 描述   盘的碎片（全部——盘亏——无盈盘）
 */
public class FragmentDish extends Fragment {

    private RecyclerView mRecyclerView;

    private DishFragmentAdapter mFragmentDishAdapter;

    private LoadingDialog mLoadingDialog;

    private List<School> mDbToSchoolList;

    private String ownershipDataSheetName;

    public static Fragment newInstance(int tab) {
        Bundle bundle = new Bundle();
        FragmentDish fragmentWin = new FragmentDish();
        bundle.putInt("tab", tab);
        fragmentWin.setArguments(bundle);
        return fragmentWin;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dish, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initRecy(view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            final int tab = bundle.getInt("tab");
            switch (tab) {
                case 0:
                    // 全部
                    findDbLitePal("全部");
                    break;
                case 1:
                    // 盘亏
                    findDbLitePal("盘亏");
                    break;
                case 2:
                    // 无盈亏
                    findDbLitePal("无盈亏");
                    break;
                default:
                    break;
            }
        }
    }

    private void initRecy(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.query_fragment_recy_dish);
        mDbToSchoolList = new ArrayList<>();
        mLoadingDialog = new LoadingDialog(getActivity(), "数据正在加载中...");
        mFragmentDishAdapter = new DishFragmentAdapter(getActivity(), mDbToSchoolList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mFragmentDishAdapter);
    }

    /**
     * 结束加载Dialog
     */
    private void stopLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * 开始加载Dialog
     */
    private void startLoading() {
        if (mLoadingDialog != null && !mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    /**
     * 根据inventoryResults（盘点结果）去数据库中查找对应数据
     *
     * @param string
     */
    private void findDbLitePal(String string) {
        startLoading();
        new AsyncTask<String, Void, Integer>() {

            @Override
            protected Integer doInBackground(String... params) {
                try {
                    mDbToSchoolList.clear();
                    if (!StrUtil.isEmpty(ownershipDataSheetName)) {
                        final int countDb = LitePal.count(School.class);
                        if (countDb > 0) {
                            if (params[0].equals("全部")) {
                                mDbToSchoolList = LitePal
                                        .where("ownershipDataSheet = ?", ownershipDataSheetName)
                                        .find(School.class);
                                mDbToSchoolList.remove(0);
                            } else {
                                mDbToSchoolList = LitePal
                                        .where(
                                                "ownershipDataSheet = ? and inventoryResults = ?",
                                                ownershipDataSheetName, params[0])
                                        .find(School.class);
                            }
                            return 1;
                        }
                    }
                    return 0;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            @Override
            protected void onPostExecute(Integer aVoid) {
                super.onPostExecute(aVoid);
                if (aVoid == 1) {
                    if (mFragmentDishAdapter != null) {
                        mFragmentDishAdapter.setDataNotify(mDbToSchoolList);
                    }
                } else {
                }
                stopLoading();
            }
        }.execute(string);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onCurrentFileNameEvent(CurrentFileName currentFile) {
        ownershipDataSheetName = currentFile.getFileName();
    }
}
