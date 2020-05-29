package com.github.lfexecleasy.util;

import com.github.lfexecleasy.entity.*;
import com.github.lfexecleasy.test.TestDept;
import com.github.lfexecleasy.test.TestUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/5/29 15:02
 * @Description : 导出数据若无要求，简易xml格式数据
 */
public class ExcelUtils {

    public static void main(String[] args) {

        //数据准备
        List<TestUser> testUsers = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            TestUser testUser = new TestUser();
            testUser.setAge("18");
            testUser.setId("12");
            testUser.setName("name");
            testUser.setTime(new Date());
            testUsers.add(testUser);
        }
        for (int i = 0; i < 1; i++) {
            TestUser testUser = new TestUser();
            testUser.setAge("1");
            testUser.setId("12");
            testUser.setName("name");
            testUser.setTime(new Date());
            testUsers.add(testUser);
        }
        //数据准备
        List<TestDept> testDept = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            TestDept testUser = new TestDept();
            testUser.setId("12");
            testUser.setDeptName("部门+" + i);
            testDept.add(testUser);
        }

        //导出全部
        ExportDataBase all1 = new ExportDataALL("sheet1", testUsers, TestUser.class);
        ExportDataBase all2 = new ExportDataALL("sheet2", testUsers, TestUser.class);
        ExportDataBase all3 = new ExportDataPart("sheet3", new String[]{"id", "部门名称"}, new String[]{"id", "deptName"}, testDept);

        all1.setExcelRuleInfo(new ExcelRuleInfo("hhh"));

        String fileName = "./TestOutputExcel.xlsx";
        ExportExcelUtils exportExcelUtils = new ExportExcelUtils();
        String s = exportExcelUtils.makeTable(all1, all2, all3);
//        System.err.println(s);
        exportExcelUtils.outPutFile(fileName, s);
        //多sheet导出
//        export(all1, all2);

    }


}
