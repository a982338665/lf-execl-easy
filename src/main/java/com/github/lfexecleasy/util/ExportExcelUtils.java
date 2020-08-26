package com.github.lfexecleasy.util;

import com.github.lfexecleasy.entity.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Mr huangye
 * URL : CSDN 皇夜_
 * createTime : 2020/5/29 17:19
 * Description : excel数据导出utl
 */
public class ExportExcelUtils {

    public String makeTable(ExportDataBase... alls) {
        StringBuffer sb = makeStyle();
        //获取每个sheet的
        for (int k = 0; k < alls.length; k++) {
            ExportDataBase all = alls[k];
            //获取
            if (all instanceof ExportDataALL) {
                //获取反射类
                Class clazz = all.getClazz();
                //获取sheet名
                String sheetName = all.getSheetName();
                //获取数据
                List list = all.getList();
                //全部数据导出
                ExcelBaseInfo headAndColume = ReflectExeclUtils.getHeadAndColume(clazz);
                //获取列对应值
                String[] includeFieldNames = headAndColume.getColume();
                //获取头信息
                String[] headers = headAndColume.getHead();
                //获取列转换关系
                Map<String, String>[] convertVal = headAndColume.getConvertVal();
                //获取规则
                ExcelRuleInfo excelRuleInfo = all.getExcelRuleInfo();
                //时间转换
                String datetimePattern = excelRuleInfo.getDatetimePattern();
                //样式
                int isStyle = excelRuleInfo.getIsStyle();
                //行头标题设置
                String title = excelRuleInfo.getTitle();
                //宽度
                Integer[] widths = excelRuleInfo.getWidths();
                // 头长度
                int headersLength = headers.length;
                setSheetName(sb, sheetName, headersLength);
                setWidth(sb, widths, headersLength);
                setFirstRowTitle(sb, title, headersLength);
                setHeadTitle(sb, headers, headersLength);
                // 构建表体数据
                setTableVal(sb, list, includeFieldNames, convertVal, datetimePattern);
                setEnd(sb);
            } else if (all instanceof ExportDataPart) {
                //获取sheet名
                String sheetName = all.getSheetName();
                //获取数据
                List list = all.getList();
                //获取列对应值
                String[] includeFieldNames = all.getIncludeFieldNames();
                String[] headers1 = all.getHeaders();
                ExcelBaseInfo headAndColume = ReflectExeclUtils.analyHeaders(headers1);
                //获取头信息
                String[] headers = headAndColume.getHead();
                //获取列转换关系
                Map<String, String>[] convertVal = headAndColume.getConvertVal();
                //获取规则
                ExcelRuleInfo excelRuleInfo = all.getExcelRuleInfo();
                //时间转换
                String datetimePattern = excelRuleInfo.getDatetimePattern();
                //样式
                int isStyle = excelRuleInfo.getIsStyle();
                //行头标题设置
                String title = excelRuleInfo.getTitle();
                //宽度
                Integer[] widths = excelRuleInfo.getWidths();
                // 头长度
                int headersLength = headers.length;
                setSheetName(sb, sheetName, headersLength);
                setWidth(sb, widths, headersLength);
                setFirstRowTitle(sb, title, headersLength);
                setHeadTitle(sb, headers, headersLength);
                // 构建表体数据
                setTableVal(sb, list, includeFieldNames, convertVal, datetimePattern);
                setEnd(sb);
            } else {
                throw new IllegalStateException("object errors : " + all.getClass().getName());
            }
        }
        sb.append("</Workbook>");
        sb.append("\n");
        return sb.toString();
    }

    private void setEnd(StringBuffer sb) {
        sb.append("</Table>");
        sb.append("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
        sb.append("\n");
        sb.append("<ProtectObjects>False</ProtectObjects>");
        sb.append("\n");
        sb.append("<ProtectScenarios>False</ProtectScenarios>");
        sb.append("\n");
        sb.append("</WorksheetOptions>");
        sb.append("\n");
        sb.append("</Worksheet>\n");
    }

    private void setTableVal(StringBuffer sb, List list, String[] includeFieldNames, Map<String, String>[] convertVal, String datetimePattern) {
        for (int j = 0; j < list.size(); j++) {
            sb.append("<Row>");
            Object t = list.get(j);
            for (int i = 0; i < includeFieldNames.length; i++) {
                // 获取属性名称
                String fieldName = includeFieldNames[i];
                // 获取转换对象
                Map<String, String> map = convertVal[i];
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                // 获取class对象
                Class tCls = t.getClass();
                // 获取属性值
                Object value = null;
                try {
                    // 获取class方法
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    // 获取属性值
                    value = getMethod.invoke(t, new Object[]{});
                    // 属性值转换
                    if (!StringUtils.isEmpty(map.get(value))) {
                        value = map.get(value);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    // 继续循环
                    continue;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    // 继续循环
                    continue;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    // 继续循环
                    continue;
                }
                // 判断值的类型后进行强制类型转换
                String textValue = convertTextVal(datetimePattern, value);
                convertOther(sb, textValue);

            }

            sb.append("</Row>");
            sb.append("\n");

        }
    }


    private void convertOther(StringBuffer sb, String textValue) {
        sb.append("<Cell><Data ss:Type=\"String\">");
        // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
        if (StringUtils.isNotEmpty(textValue)) {
            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
            Matcher matcher = p.matcher(textValue);
            if (matcher.matches()) {
                // 是数字当作double处理
                sb.append(Double.parseDouble(textValue));
            } else {
                sb.append(textValue);
            }

        }

        sb.append("</Data></Cell>");
    }

    private String convertTextVal(String datetimePattern, Object value) {
        String textValue;
        if (value instanceof Integer) {
            // int value = ((Integer) value).intValue();
            textValue = value.toString();
        } else if (value instanceof String) {
            // String s = (String) value;
            textValue = value.toString();
        } else if (value instanceof Double) {
            // double d = ((Double) value).doubleValue();
            textValue = String.format("%.2f", value);
        } else if (value instanceof Float) {
            // float f = ((Float) value).floatValue();
            textValue = value.toString();
        } else if (value instanceof Long) {
            // long l = ((Long) value).longValue();
            textValue = value.toString();
        } else if (value instanceof Boolean) {
            // boolean b = ((Boolean) value).booleanValue();
            textValue = value.toString();
        } else if (value instanceof Date) {
            Date date = (Date) value;
            SimpleDateFormat sdf = new SimpleDateFormat(datetimePattern);
            textValue = sdf.format(date);
        } else if ((value instanceof BigDecimal)) {
            textValue = value.toString();
        } else {
            textValue = "未知类型" + value.toString();
//                            if (value != null) {
//                                continue;
//                            }
        }
        return textValue;
    }

    private void setHeadTitle(StringBuffer sb, String[] headers, int headersLength) {
        // 输出列头
        sb.append("<Row>");
        for (int i = 0; i < headersLength; i++) {
            sb.append("<Cell ss:StyleID=\"header\"><Data ss:Type=\"String\">" + headers[i] + "</Data></Cell>");
        }
        sb.append("</Row>");
    }

    private void setSheetName(StringBuffer sb, String sheetName, int headersLength) {
        sb.append("<Worksheet ss:Name=\"" + sheetName + "\">");
        sb.append("\n");
        sb.append("<Table ss:ExpandedColumnCount=\"" + headersLength
                + "\" ss:ExpandedRowCount=\"1000000\" x:FullColumns=\"1\" x:FullRows=\"1\">");
        sb.append("\n");
    }

    private void setWidth(StringBuffer sb, Integer[] widths, int headersLength) {
        if (widths.length > 0) {
            if (widths.length > 1) {
                for (int i = 0; i < headersLength; i++) {
                    sb.append("<Column ss:AutoFitWidth=\"0\" ss:Width=\"" + widths[i] + "\"/>");
                }
            } else {
                for (int i = 0; i < headersLength; i++) {
                    sb.append("<Column ss:AutoFitWidth=\"0\" ss:Width=\"" + widths[0] + "\"/>");
                }
            }
        }
    }

    private void setFirstRowTitle(StringBuffer sb, String title, int headersLength) {
        // 输出第一行的标题
        if (!StringUtils.isEmpty(title)) {
            //ss:StyleID可以加row或者Cell，加在Row，整行（包括空的Cell）都有，加上Cell，只有Cell才有。
            sb.append("<Row  ss:Height=\"30\">");
            sb.append("<Cell ss:StyleID=\"header\" ss:MergeAcross=\"" + (headersLength - 1) + "\"><Data ss:Type=\"String\">" + title + "</Data></Cell>");
            sb.append("</Row>");
        }
    }


    private StringBuffer makeStyle() {
        // 创建一个excel应用文件
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\"?>");
        sb.append("\n");
        sb.append("<?mso-application progid=\"Excel.Sheet\"?>");
        sb.append("\n");
        sb.append("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"");
        sb.append("\n");
        sb.append(" xmlns:o=\"urn:schemas-microsoft-com:office:office\"");
        sb.append("\n");
        sb.append(" xmlns:x=\"urn:schemas-microsoft-com:office:excel\"");
        sb.append("\n");
        sb.append(" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"");
        sb.append("\n");
        sb.append(" xmlns:html=\"http://www.w3.org/TR/REC-html40\">");
        sb.append("\n");

        sb.append("<Styles>\n");

        /*设置列头样式*/
        sb.append("<Style ss:ID=\"header\" ss:Name=\"header\">\n");//ss:ID=“header”对应下面的Row ss:StyleID=“header”
        sb.append("<Interior ss:Color=\"#cccccc\" ss:Pattern=\"Solid\"/>\n");// 设置背景颜色
        sb.append("<Font ss:FontName=\"微软雅黑\" x:CharSet=\"134\" ss:Bold=\"Bolder\" ss:Size=\"12\"/>\n");//设置字体
        sb.append("</Style>\n");

        /*其它默认样式设置*/
        sb.append("<Style ss:ID=\"Default\" ss:Name=\"Normal\">\n");
        //sb.append("<Alignment ss:Vertical=\"Center\"/>\n");
        sb.append("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>\n");// 左中右设置，一个是水平，一个是垂直
        sb.append("<Borders>\n");
        sb.append("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Color=\"#666\" ss:Weight=\"1\"/>\n");//左边框设置
        sb.append("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Color=\"#666\" ss:Weight=\"1\"/>\n");//右边框设置
        sb.append("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Color=\"#666\" ss:Weight=\"1\"/>\n");//下边框设置
        sb.append("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Color=\"#666\" ss:Weight=\"1\"/>\n");//上边框设置
        sb.append("</Borders>\n");
        sb.append("<Font ss:FontName=\"宋体\" x:CharSet=\"134\" ss:Size=\"12\"/>\n");
        sb.append("<Interior/>\n");
        sb.append("<NumberFormat/>\n");
        sb.append("<Protection/>\n");
        sb.append("</Style>\n");

        sb.append("</Styles>\n");
        return sb;
    }

    /**
     * 文件输出
     * @param fileName
     * @param content
     */
    public static void outPutFile(String fileName, String content) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(fileName);
            outputStream.write(content.getBytes());
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 文件输出
     * @param outputStream
     * @param content
     */
    public static void outPutResponse(OutputStream outputStream, String content) {
        try {
            outputStream.write(content.getBytes());
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
