package com.gg.nnr.littlemeng.mvp.presenter;


import com.gg.nnr.littlemeng.entity.NewsListDto;
import com.gg.nnr.littlemeng.mvp.listener.OnLoadDataListListener;
import com.gg.nnr.littlemeng.mvp.model.NewsListModel;
import com.gg.nnr.littlemeng.mvp.view.NewsListView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class NewsListPresent implements OnLoadDataListListener<List<NewsListDto>> {
    private NewsListView mView;
    private NewsListModel mModel;

    public NewsListPresent(NewsListView mView) {
        this.mView = mView;
        this.mModel = new NewsListModel();
        mView.showProgress();
    }

    @Override
    public void onSuccess(List<NewsListDto> data) {
        if (data.size() == 0) {
            mView.showLoadCompleteAllData();
        } else {
            //新增自动加载的的数据
            mView.addDatas(data);
        }
        mView.hideProgress();
    }

    @Override
    public void onFailure(Throwable e) {
        mView.showLoadFailMsg();
    }

    public void LoadData(String channelName, String page) {
        mModel.LoadData(channelName, page, this);
    }
}
