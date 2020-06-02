package com.github.lfexecleasy.entity;

import java.util.Arrays;

/**
 * @author : Mr huangye
 * URL : CSDN 皇夜_
 * createTime : 2020/5/29 17:25
 * Description : excel的规则信息，有默认值也可以设置
 */
public class ExcelRuleInfo {

    /**
     * 日期格式转换
     */
    private String datetimePattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 宽度
     */
    private Integer[] widths = new Integer[]{100};

    /**
     * 样式模板选择
     * 默认为 0 ，不选择样式
     */
    private int isStyle = 0;

    /**
     * 是否保留第一行标题头
     * 默认不要标题头，若有值则保留，并填写内容
     */
    private String title = null;



    public ExcelRuleInfo() {
    }


    public ExcelRuleInfo(String datetimePattern, Integer[] widths, int isStyle, String title) {
        this.datetimePattern = datetimePattern;
        this.widths = widths;
        this.isStyle = isStyle;
        this.title = title;
    }
    public ExcelRuleInfo (Integer[] widths, int isStyle, String title) {
        this.widths = widths;
        this.isStyle = isStyle;
        this.title = title;
    }
    public ExcelRuleInfo (Integer[] widths,  String title) {
        this.widths = widths;
        this.isStyle = isStyle;
        this.title = title;
    }
    public ExcelRuleInfo (String title) {
        this.widths = widths;
        this.isStyle = isStyle;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsStyle() {
        return isStyle;
    }

    public void setIsStyle(int isStyle) {
        this.isStyle = isStyle;
    }

    public String getDatetimePattern() {
        return datetimePattern;
    }

    public void setDatetimePattern(String datetimePattern) {
        this.datetimePattern = datetimePattern;
    }

    public Integer[] getWidths() {
        return widths;
    }

    public void setWidths(Integer[] widths) {
        this.widths = widths;
    }

    @Override
    public String toString() {
        return "ExcelRuleInfo{" +
                "datetimePattern='" + datetimePattern + '\'' +
                ", widths=" + Arrays.toString(widths) +
                '}';
    }
}
