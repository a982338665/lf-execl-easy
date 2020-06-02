package com.github.lfexecleasy.entity;

import java.util.List;

/**
 * @author : Mr huangye
 * URL : CSDN 皇夜_
 * createTime : 2020/5/29 18:00
 * Description : 数据导出基类
 */
public class ExportDataBase {
    /**
     * sheet的名称
     */
    private String sheetName ;
    /**
     * 字段数据列表
     */
    private List<Object> list;
    /**
     * 映射的类
     */
    private Class clazz;
    /**
     * 列名集合
     */
    private String[] headers ;
    /**
     * 列名对应的字段值集合
     */
    private String[] includeFieldNames ;
    /**
     * 数据导出规则定义
     */
    private ExcelRuleInfo excelRuleInfo = new ExcelRuleInfo();

    public ExcelRuleInfo getExcelRuleInfo() {
        return excelRuleInfo;
    }

    public void setExcelRuleInfo(ExcelRuleInfo excelRuleInfo) {
        this.excelRuleInfo = excelRuleInfo;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public String[] getIncludeFieldNames() {
        return includeFieldNames;
    }

    public void setIncludeFieldNames(String[] includeFieldNames) {
        this.includeFieldNames = includeFieldNames;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
