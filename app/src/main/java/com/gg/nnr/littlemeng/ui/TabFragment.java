package com.gg.nnr.littlemeng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gg.nnr.littlemeng.R;
import com.gg.nnr.littlemeng.constant.Constant;
import com.gg.nnr.littlemeng.db.DBManager;
import com.gg.nnr.littlemeng.entity.NewsListDto;
import com.gg.nnr.littlemeng.mvp.presenter.NewsListPresent;
import com.gg.nnr.littlemeng.mvp.view.NewsListView;
import com.gg.nnr.littlemeng.ui.adapter.ListViewAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.SmartTab.UtilsV4.v4.FragmentPagerItem;
import com.xiaochao.lcrapiddeveloplibrary.container.RotationHeader;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class TabFragment extends Fragment implements BaseQuickAdapter.RequestLoadMoreListener,SpringView.OnFreshListener,NewsListView  {
    RecyclerView mRecyclerView;
    ProgressActivity progress;
    private BaseQuickAdapter mQuickAdapter;
    private int PageIndex=1;
    private SpringView springView;
    private NewsListPresent present;
    Bundle bundle;
    int position=0;
    DBManager mgr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bundle=savedInstanceState;
        position = FragmentPagerItem.getPosition(getArguments());
        initView(view);
        initListener();
    }

    private void initView(View root) {
        mRecyclerView = (RecyclerView)root.findViewById(R.id.rv_list);
        springView = (SpringView)root.findViewById(R.id.springview);
        //设置下拉刷新监听
        springView.setListener(this);
        //设置下拉刷新样式
        springView.setHeader(new RotationHeader(getActivity()));
        //springView.setFooter(new RotationFooter(this));mRecyclerView内部集成的自动加载  上啦加载用不上   在其他View使用
        progress = (ProgressActivity)root.findViewById(R.id.progress);
        //设置RecyclerView的显示模式  当前List模式
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //如果Item高度固定  增加该属性能够提高效率
        mRecyclerView.setHasFixedSize(true);
        //设置页面为加载中..
        progress.showLoading();
        //设置适配器
        mQuickAdapter = new ListViewAdapter(R.layout.list_view_item_layout,null);
        //设置加载动画
        mQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //设置是否自动加载以及加载个数
        mQuickAdapter.openLoadMore(6,true);
        //将适配器添加到RecyclerView
        mRecyclerView.setAdapter(mQuickAdapter);
        present = new NewsListPresent(this);
        //请求网络数据
        mgr = new DBManager(getContext());
        present.LoadData(mgr.query("1")[position], String.valueOf(PageIndex));

    }
    private void initListener() {
        //设置自动加载监听
        mQuickAdapter.setOnLoadMoreListener(this);

        mQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsListDto newsListDto=(NewsListDto)mQuickAdapter.getData().get(position);
                Intent intent=new Intent(getActivity(),NewsDetailActivity.class);
                intent.putExtra("link",newsListDto.getLink());
                startActivity(intent);
            }
        });
        mQuickAdapter.setOnRecyclerViewItemLongClickListener(new BaseQuickAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                NewsListDto newsListDto=(NewsListDto)mQuickAdapter.getData().get(position);
                return true;
            }
        });
    }

    @Override
    public void onRefresh() {
        PageIndex=1;
        present.LoadData(mgr.query("1")[position],String.valueOf(PageIndex));
    }

    @Override
    public void onLoadmore() {

    }

    @Override
    public void onLoadMoreRequested() {
        PageIndex++;
        present.LoadData(mgr.query("1")[position],String.valueOf(PageIndex));

    }

    @Override
    public void showProgress() {
        progress.showLoading();
    }

    @Override
    public void hideProgress() {
        progress.showContent();
    }

    @Override
    public void newDatas(List<NewsListDto> newsList) {
        mQuickAdapter.setNewData(newsList);//新增数据
        mQuickAdapter.openLoadMore(10,true);//设置是否可以下拉加载  以及加载条数
        springView.onFinishFreshAndLoad();//刷新完成
    }

    @Override
    public void addDatas(List<NewsListDto> addList) {
        //新增自动加载的的数据
        mQuickAdapter.notifyDataChangedAfterLoadMore(addList, true);
    }

    @Override
    public void showLoadFailMsg() {
        progress.showError(getResources().getDrawable(R.mipmap.ic_launcher), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageIndex=1;
                present.LoadData(mgr.query("1")[position],String.valueOf(PageIndex));
            }
        });
    }

    @Override
    public void showLoadCompleteAllData() {
        mQuickAdapter.notifyDataChangedAfterLoadMore(false);
        View view = getLayoutInflater(bundle).inflate(R.layout.not_loading, (ViewGroup) mRecyclerView.getParent(), false);
        mQuickAdapter.addFooterView(view);
    }

    @Override
    public void showNoData() {
        progress.showEmpty(getResources().getDrawable(R.mipmap.ic_launcher),Constant.EMPTY_TITLE,Constant.EMPTY_CONTEXT);

    }
}
