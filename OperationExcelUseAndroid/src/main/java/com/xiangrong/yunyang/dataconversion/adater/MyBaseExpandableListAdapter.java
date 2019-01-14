package com.xiangrong.yunyang.dataconversion.adater;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.xiangrong.yunyang.dataconversion.R;
import com.xiangrong.yunyang.dataconversion.entity.GroupExpand;
import com.xiangrong.yunyang.dataconversion.entity.ItemExpand;

import java.util.ArrayList;

/**
 * 作者    yunyang
 * 时间    2019/1/9 15:15
 * 文件    DataConversion
 * 描述   ExpandableListView的适配器
 */
public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    private ArrayList<GroupExpand> gData;
    private ArrayList<ArrayList<ItemExpand>> iData;
    private Context mContext;

    public MyBaseExpandableListAdapter(ArrayList<GroupExpand> gData, ArrayList<ArrayList<ItemExpand>> iData, Context mContext) {
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return iData.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return gData.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return iData.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    // 取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup groupHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_exlist_group, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.tv_group_name = (AppCompatTextView) convertView.findViewById(R.id.tv_group_name);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tv_group_name.setText(gData.get(groupPosition).getGroupName());
        return convertView;
    }

    // 取得显示给定分组给定子位置的数据用的视图
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_exlist_item, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.tv_name = (AppCompatTextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.tv_name.setText(iData.get(groupPosition).get(childPosition).getItemName());
        return convertView;
    }

    //设置子列表是否可选中
    @Override
    public boolean isChildSelectable(int GroupExpandPosition, int childPosition) {
        return true;
    }

    private static class ViewHolderGroup {
        private AppCompatTextView tv_group_name;
    }

    private static class ViewHolderItem {
        private AppCompatTextView tv_name;
    }

}
