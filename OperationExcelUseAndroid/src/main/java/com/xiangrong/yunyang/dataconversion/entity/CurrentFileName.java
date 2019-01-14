package com.xiangrong.yunyang.dataconversion.entity;

/**
 * 作者    yunyang
 * 时间    2019/1/11 8:53
 * 文件    DataConversion
 * 描述   FragmentDish查询需要的 所属数据库 列
 */
public class CurrentFileName {

    private String fileName;

    public CurrentFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
