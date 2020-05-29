package com.github.lfexecleasy.entity;

import java.util.Arrays;
import java.util.Map;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/5/29 16:39
 * @Description : excel的基本信息数据
 */
public class ExcelBaseInfo {


    /**
     * 列头
     */
    private String[] head;
    /**
     * 列值字段
     */
    private String[] colume;
    /**
     * 转换信息
     */
    private Map<String, String>[] convertVal;

    public String[] getHead() {
        return head;
    }

    public void setHead(String[] head) {
        this.head = head;
    }

    public String[] getColume() {
        return colume;
    }

    public void setColume(String[] colume) {
        this.colume = colume;
    }

    public Map<String, String>[] getConvertVal() {
        return convertVal;
    }

    public void setConvertVal(Map<String, String>[] convertVal) {
        this.convertVal = convertVal;
    }


}
