package com.github.lfexecleasy.test;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/5/29 17:34
 * @Description :
 */
public class test extends TestDept {

    private String  x = "q";

    public static void main(String[] args) {
        TestDept t = new test();
        System.err.println(t);
        System.err.println(t instanceof test);

    }
}
