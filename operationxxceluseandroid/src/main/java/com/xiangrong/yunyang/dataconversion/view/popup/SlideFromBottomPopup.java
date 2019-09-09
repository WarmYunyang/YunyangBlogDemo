package com.xiangrong.yunyang.dataconversion.view.popup;

import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ExpandableListView;

import com.xiangrong.yunyang.dataconversion.R;
import com.xiangrong.yunyang.dataconversion.adater.MyBaseExpandableListAdapter;
import com.xiangrong.yunyang.dataconversion.entity.ExpandMessage;
import com.xiangrong.yunyang.dataconversion.entity.GroupExpand;
import com.xiangrong.yunyang.dataconversion.entity.ItemExpand;
import com.xiangrong.yunyang.dataconversion.entity.School;
import com.xiangrong.yunyang.dataconversion.entity.UnderWayFileLists;
import com.xiangrong.yunyang.dataconversion.utils.FileUtil;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import razerdp.basepopup.BasePopupWindow;

/**
 * 作者    yunyang
 * 时间    2019/1/9 14:17
 * 文件    DataConversion
 * 描述   从底部滑上来的popup
 */
public class SlideFromBottomPopup extends BasePopupWindow {

    private ExpandableListView expand_list;

    private ArrayList<GroupExpand> mGroupData = null;
    private ArrayList<ArrayList<ItemExpand>> mItemData = null;
    private Context mContext;

    private String mFilePath;
    private MyBaseExpandableListAdapter myAdapter = null;

    private Set<String> mHashSet;
    private File mFileDir;
    private Vector<File> mSubFileVector;
    private List<School> mSchoolsExcelName;
    private ArrayList<ItemExpand> lData = null;

    private String currentFileName;

    public SlideFromBottomPopup(Context context, String filePath) {
        super(context);
        mContext = getContext();
        mFilePath = filePath;
        setPopupGravity(Gravity.BOTTOM);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        mSchoolsExcelName = new ArrayList<>();
        mHashSet = new HashSet<>();
        mSubFileVector = new Vector<File>();

        mGroupData = new ArrayList<GroupExpand>();
        mItemData = new ArrayList<ArrayList<ItemExpand>>();
        mGroupData.add(new GroupExpand("进行中的清查任务"));
        mGroupData.add(new GroupExpand("导入新的清查任务"));
    }

    private void initEvent() {
        //为列表设置点击事件
        expand_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                currentFileName = mItemData.get(groupPosition).get(childPosition).getItemName();
                EventBus.getDefault().post(new ExpandMessage(currentFileName, mSubFileVector));
                if (mItemData.get(0).size() > 0) {
                    EventBus.getDefault().post(new UnderWayFileLists(currentFileName, mHashSet));
                }
                dismiss();
                return true;
            }
        });


    }

    public void newPopupBottomShow() {
        new AsyncTask<String, Void, Integer>() {

            @Override
            protected Integer doInBackground(String... params) {
                try {
                    mItemData.clear();
                    mSubFileVector.clear();

                    FileUtil.findRecursion(mFilePath, mSubFileVector);

                    // 本地数据库中的表
                    lData = new ArrayList<ItemExpand>();
                    if (LitePal.count(School.class) <= 0) {
                    } else {
                        // 拿到所有的Excel_Sheet表名，遍历数据库_所属数据库列，去重后，拿到所有表名
                        mSchoolsExcelName = LitePal
                                .select("ownershipDataSheet")
                                .find(School.class);
                        if (mSchoolsExcelName.size() > 0) {
                            for (int i = 0; i < mSchoolsExcelName.size(); i++) {
                                mHashSet.add(mSchoolsExcelName.get(i).getOwnershipDataSheet());
                            }
                            for (String str :
                                    mHashSet) {
                                lData.add(new ItemExpand(str));
                            }
                        }
                    }
                    mItemData.add(lData);

                    // 本地文件存储的表
                    lData = new ArrayList<ItemExpand>();
                    if (mSubFileVector.size() <= 0) {
                    } else {
                        for (File file :
                                mSubFileVector) {
                            lData.add(new ItemExpand(file.getName()));
                        }
                    }
                    mItemData.add(lData);
                    return 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            @Override
            protected void onPostExecute(Integer aVoid) {
                super.onPostExecute(aVoid);
                if (aVoid == 1) {
                    myAdapter = new MyBaseExpandableListAdapter(mGroupData, mItemData, mContext);
                    expand_list.setAdapter(myAdapter);
                    showPopupWindow();
                } else {

                }
            }
        }.execute();
    }

    private void initView() {
        expand_list = (ExpandableListView) findViewById(R.id.expand_list);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return getTranslateVerticalAnimation(1f, 0, 500);
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getTranslateVerticalAnimation(0, 1f, 500);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_slide_from_bottom);
    }

}
