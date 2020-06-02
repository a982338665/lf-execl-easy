package com.github.lfexecleasy.entity;

import java.util.List;

/**
 * @author : Mr huangye
 * URL : CSDN 皇夜_
 * createTime : 2020/5/29 16:00
 * Description : 导出数据参数封装类：部分导出
 */
public class ExportDataPart extends ExportDataBase {

    /**
     * sheet的名称
     */
    private String sheetName ;
    /**
     * 列名集合
     */
    private String[] headers ;
    /**
     * 列名对应的字段值集合
     */
    private String[] includeFieldNames ;
    /**
     * 字段数据列表
     */
    private List list;
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

    public ExportDataPart() {
    }

    public ExportDataPart(String sheetName, String[] headers, String[] includeFieldNames, List list) {
        this.sheetName = sheetName;
        this.headers = headers;
        this.includeFieldNames = includeFieldNames;
        this.list = list;
    }

    @Override
    public String getSheetName() {
        return sheetName;
    }

    @Override
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    @Override
    public String[] getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    @Override
    public String[] getIncludeFieldNames() {
        return includeFieldNames;
    }

    @Override
    public void setIncludeFieldNames(String[] includeFieldNames) {
        this.includeFieldNames = includeFieldNames;
    }

    @Override
    public List getList() {
        return list;
    }

    @Override
    public void setList(List list) {
        this.list = list;
    }
}
