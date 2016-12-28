package com.gg.nnr.littlemeng.entity;

/**
 * Created by Administrator on 2016/12/11 0011.
 */
public class PageBeanResult <T> {
    String currentPage;
    String allNum;
    String maxResult;
    String allPages;
    T contentlist;

    public T getContentlist() {
        return contentlist;
    }

    public void setContentlist(T contentlist) {
        this.contentlist = contentlist;
    }
}
