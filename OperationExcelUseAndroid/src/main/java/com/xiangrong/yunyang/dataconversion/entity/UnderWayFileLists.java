package com.xiangrong.yunyang.dataconversion.entity;

import java.util.Set;

/**
 * 作者    yunyang
 * 时间    2019/1/11 11:00
 * 文件    DataConversion
 * 描述   正在进行的任务
 */
public class UnderWayFileLists {

    private String fileName;

    private Set<String> mSetFiles;

    public UnderWayFileLists(String fileName, Set<String> setFiles) {
        this.fileName = fileName;
        mSetFiles = setFiles;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Set<String> getSetFiles() {
        return mSetFiles;
    }

    public void setSetFiles(Set<String> setFiles) {
        mSetFiles = setFiles;
    }
}
