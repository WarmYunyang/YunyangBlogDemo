package com.xiangrong.yunyang.dataconversion.entity;

/**
 * 作者    yunyang
 * 时间    2019/1/9 14:39
 * 文件    DataConversion
 * 描述   ExpandableListView的Group的Entity
 */
public class GroupExpand {

    private String groupName;

    public GroupExpand(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
