package com.xiangrong.yunyang.dataconversion.entity;

import java.io.File;
import java.util.Vector;

/**
 * 作者    yunyang
 * 时间    2019/1/9 15:27
 * 文件    DataConversion
 * 描述   ExpandableListView的Adapter传递给MainActivity的信息
 */
public class ExpandMessage {

    private String fileName;

    private Vector<File> mSubFileVector;

    public ExpandMessage(String fileName, Vector<File> subFileVector) {
        this.fileName = fileName;
        mSubFileVector = subFileVector;
    }

    public Vector<File> getSubFileVector() {
        return mSubFileVector;
    }

    public void setSubFileVector(Vector<File> subFileVector) {
        mSubFileVector = subFileVector;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
