package com.gg.nnr.littlemeng.mvp.listener;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public interface OnLoadDataListListener<T> {
    void onSuccess(T data);
    void onFailure(Throwable e);
}