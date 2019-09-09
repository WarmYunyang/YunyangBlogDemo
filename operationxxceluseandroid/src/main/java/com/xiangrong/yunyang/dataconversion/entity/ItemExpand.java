package com.xiangrong.yunyang.dataconversion.entity;

/**
 * 作者    yunyang
 * 时间    2019/1/9 14:39
 * 文件    DataConversion
 * 描述   ExpandableListView的Item的Entity
 */
public class ItemExpand {

    private String itemName;

    public ItemExpand(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
