package com.gg.nnr.littlemeng.entity;

/**
 * Created by Administrator on 2016/12/11 0011.
 */
public class BodyResult <T>{
    String ret_code;
    T pagebean;

    public T getPagebean() {
        return pagebean;
    }

    public void setPagebean(T pagebean) {
        this.pagebean = pagebean;
    }
}
