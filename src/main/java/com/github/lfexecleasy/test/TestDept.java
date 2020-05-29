package com.github.lfexecleasy.test;

import com.github.lfexecleasy.anno.LFColume;

import java.util.Date;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/5/29 16:19
 */
public class TestDept {

    @LFColume("id")
    private String id;
    @LFColume("名称")
    private String deptName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
