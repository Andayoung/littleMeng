package com.gg.nnr.littlemeng.mvp.model;

import android.util.Log;

import com.gg.nnr.littlemeng.data.HttpData;
import com.gg.nnr.littlemeng.entity.NewsListDto;
import com.gg.nnr.littlemeng.mvp.listener.OnLoadDataListListener;

import java.util.List;

import rx.Observer;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class NewsListModel {
    public void LoadData(String channelName,String page, final OnLoadDataListListener listener){
        HttpData.getInstance().HttpDataToNewsList(channelName, page, new Observer<List<NewsListDto>>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                //设置页面为加载错误
                listener.onFailure(e);
            }

            @Override
            public void onNext(List<NewsListDto> data) {
                listener.onSuccess(data);
            }
        });
    }

}
