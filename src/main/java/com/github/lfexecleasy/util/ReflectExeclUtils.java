package com.github.lfexecleasy.util;

import com.github.lfexecleasy.anno.LFColume;
import com.github.lfexecleasy.entity.ExcelBaseInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Mr huangye
 * URL : CSDN 皇夜_
 * createTime : 2020/5/29 16:26
 * Description : excel 导出用到的反射方法封装
 */
public class ReflectExeclUtils {

    /**
     * 解析 列头，列值，列转换
     *
     * @param t
     * @return
     */
    public static ExcelBaseInfo getHeadAndColume(Class t) {
        //获取本类所有声明的字段
        Field[] fs2 = t.getDeclaredFields();
        List<Field> list = new ArrayList<>();
        //过滤要排除的字段：没有注解的，序列化id
        for (int i = 0; i < fs2.length; i++) {
            Field f = fs2[i];
            f.setAccessible(true);//可以使得private临时变成public，变成可访问的
            //获取属性名
            LFColume annotationff = f.getAnnotation(LFColume.class);
            if (annotationff != null) {
                list.add(f);
            }
        }
        String[] head = new String[list.size()];
        String[] colume = new String[list.size()];
        Map<String, String>[] convertVal = new HashMap[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Field f = list.get(i);
            f.setAccessible(true);//可以使得private临时变成public，变成可访问的
            //获取属性名
            LFColume annotationff = f.getAnnotation(LFColume.class);
            if (annotationff != null) {
                Map<String, String> mapp = new HashMap<>();
                String valueff = annotationff.value();
                String[] split = valueff.split("@");
                //列头
                head[i] = split[0];
                //列值转换
                if (split.length == 2) {
                    //列值转换:@ 1-普通 2-项目 3-增库
                    String field = split[1];
                    String[] val = field.split(" ");
                    for (int j = 0; j < val.length; j++) {
                        String s = val[j];
                        if (s != null) {
                            String[] split1 = s.split("-");
                            mapp.put(split1[0].trim(), split1[1].trim());
                        }
                    }

                }
                convertVal[i] = mapp;
                colume[i] = f.getName();
            }
        }
        ExcelBaseInfo excelBaseInfo = new ExcelBaseInfo();
        excelBaseInfo.setColume(colume);
        excelBaseInfo.setConvertVal(convertVal);
        excelBaseInfo.setHead(head);
        return excelBaseInfo;
    }

    public static ExcelBaseInfo analyHeaders(String[] headers) {
        Map<String, String>[] convertVal = new HashMap[headers.length];
        String[] head = new String[headers.length];
        try {
            for (int i = 0; i < headers.length; i++) {
                String valueff = headers[i];
                Map<String, String> mapp = new HashMap<>();
                String[] split = valueff.split("@");
                //列头
                head[i] = split[0];
                //列值转换
                if (split.length == 2) {
                    //列值转换:@ 1-普通 2-项目 3-增库
                    String field = split[1];
                    String[] val = field.split(" ");
                    for (int j = 0; j < val.length; j++) {
                        String s = val[j];
                        if (s != null) {
                            String[] split1 = s.split("-");
                            mapp.put(split1[0].trim(), split1[1].trim());
                        }
                    }

                }
                convertVal[i] = mapp;
            }
        } catch (Exception e) {
            throw new IllegalStateException("convert colume error:" + e.getMessage());
        }
        ExcelBaseInfo excelBaseInfo = new ExcelBaseInfo();
        excelBaseInfo.setHead(head);
        excelBaseInfo.setConvertVal(convertVal);
        return excelBaseInfo;
    }

//    public static void main(String[] args) {
//        ExcelBaseInfo headAndColume = ReflectExeclUtils.getHeadAndColume(TestUser.class);
//        System.err.println(headAndColume);
//    }
}
