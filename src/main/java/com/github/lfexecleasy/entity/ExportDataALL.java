package com.github.lfexecleasy.entity;

import java.util.List;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/5/29 16:00
 * @Description : 导出数据参数封装： 全部导出
 */
public class ExportDataALL extends ExportDataBase {

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
     * 数据导出规则定义
     */
    private ExcelRuleInfo excelRuleInfo = new ExcelRuleInfo();

    public ExcelRuleInfo getExcelRuleInfo() {
        return excelRuleInfo;
    }

    public void setExcelRuleInfo(ExcelRuleInfo excelRuleInfo) {
        this.excelRuleInfo = excelRuleInfo;
    }

    public ExportDataALL() {
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

    public ExportDataALL(String sheetName, List list, Class clazz) {
        this.sheetName = sheetName;
        this.list = list;
        this.clazz = clazz;
    }
}
