package com.gg.nnr.littlemeng.mvp.view;

import com.gg.nnr.littlemeng.entity.NewsListDto;

import java.util.List;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public interface NewsListView {
    //显示加载页
    void showProgress();

    //关闭加载页
    void hideProgress();

    //加载新数据
    void newDatas(List<NewsListDto> newsList);

    //添加更多数据
    void addDatas(List<NewsListDto> addList);

    //显示加载失败
    void showLoadFailMsg();

    //显示已加载所有数据
    void showLoadCompleteAllData();

    //显示无数据
    void showNoData();


}
