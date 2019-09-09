package com.xiangrong.yunyang.dataconversion.utils;

import android.annotation.SuppressLint;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者    yunyang
 * 时间    2019/1/4 12:00
 * 文件    DataConversion
 * 描述   字符串工具类
 */
public class StrUtil {

    /*
     * 左对齐，空格补齐
     */
    public static String padLeft(String s, int length) {
        byte[] bs = new byte[length];
        byte[] ss = s.getBytes();
        Arrays.fill(bs, (byte) 32);
        // System.arraycopy(ss, 0, bs, length - ss.length, ss.length);
        System.arraycopy(ss, 0, bs, 0, ss.length);
        return new String(bs);
    }

    public static int getWordsCount(String s) {

        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }

    /*
     * 获得汉字个数
     */
    public static int getGBCount(String str) {
        int count = 0;
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count = count + 1;
            }
        }
        return count;
    }

    @SuppressLint("SimpleDateFormat")
    private static DateFormat timeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public static java.util.Date Convert2Date(String time) {
        if (isNullOrEmpty(time)) {
            return new Date();
        }

        Date date = null;
        try {
            date = timeFormat.parse(time);
        } catch (ParseException pe) {
            date = new Date();
        }
        return date;
    }

    /*
     * 字符串是否为NULL或空
     */
    public static boolean isNullOrEmpty(String str) {
        boolean flag = false;
        if (str == null) {
            flag = true;
        } else if (str.trim().equalsIgnoreCase("null")) {
            flag = true;
        } else {
            if ("".equals(str.trim())) {
                flag = true;
            }
        }
        return flag;
    }

    /*
     * 将yyyy-MM-dd HH:mm:ss 转成yyyyMMddHHmmss
     */
    @SuppressLint("SimpleDateFormat")
    public static String formatTimeStr(String timeStr) {
        String newTimeStr = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        try {
            Date d = dateFormat.parse(timeStr);
            dateFormat.applyPattern("yyyyMMddHHmmss");
            newTimeStr = dateFormat.format(d);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newTimeStr;
    }

    /*
     * 数组变成字符串
     */
    public static String arrayToString(String[] words) {
        StringBuilder strSb = new StringBuilder();
        if (words != null && words.length > 0) {
            for (int i = 0; i < words.length; i++) {
                strSb.append(words[i]);
            }
        }
        return strSb.toString();
    }

    public static boolean isEmpty(String str) {
        return isNullOrEmpty(str);
    }

    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(
                        "UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * encoded in utf-8, if exception, return defultReturn
     *
     * @param str
     * @param defultReturn
     * @return
     */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }

    public static String FilterSpecial(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * String数据类型转换为int数据类型，取掉小数点|判空操作
     *
     * @param stringText
     * @return
     */
    public static String isNullOrEmptyAndSub(String stringText) {
        if (isNullOrEmpty(stringText)) {
            stringText = "0";
        } else {
            if (stringText.contains(".")) {
                final int idx = stringText.lastIndexOf(".");
                if (idx > 0) {
                    stringText = stringText.substring(0, idx);
                }
            }
        }
        return stringText;
    }

    /**
     * 去掉文件后缀，返回文件名称
     *
     * @param fileName
     * @return 返回文件名称
     */
    public static String getFileNameNoEx(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length()))) {
                return fileName.substring(0, dot);
            }
        }
        return fileName;

    }
}
