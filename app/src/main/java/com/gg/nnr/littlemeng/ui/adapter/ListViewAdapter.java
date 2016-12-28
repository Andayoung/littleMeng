package com.gg.nnr.littlemeng.ui.adapter;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gg.nnr.littlemeng.R;
import com.gg.nnr.littlemeng.entity.NewsListDto;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/7/1.
 */
public class ListViewAdapter extends BaseQuickAdapter<NewsListDto> {
    public ListViewAdapter(int layoutResId, List<NewsListDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsListDto item) {
            if(item.getImageurls().length==0){
                helper.getView(R.id.book_info_image_url).setVisibility(View.GONE);
                helper.getView(R.id.three_images).setVisibility(View.GONE);
            }else if(item.getImageurls().length<=2){
                helper.getView(R.id.book_info_image_url).setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(item.getImageurls()[0])
                        .crossFade()
                        .placeholder(R.mipmap.ic_launcher)
                        .into((ImageView) helper.getView(R.id.book_info_image_url));
                helper.getView(R.id.three_images).setVisibility(View.GONE);
            }else if(item.getImageurls().length>=3){
                helper.getView(R.id.book_info_image_url).setVisibility(View.GONE);
                helper.getView(R.id.three_images).setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(item.getImageurls()[0])
                        .crossFade()
                        .placeholder(R.mipmap.ic_launcher)
                        .into((ImageView) helper.getView(R.id.news_image_url1));
                Glide.with(mContext)
                        .load(item.getImageurls()[1])
                        .crossFade()
                        .placeholder(R.mipmap.ic_launcher)
                        .into((ImageView) helper.getView(R.id.news_image_url2));
                Glide.with(mContext)
                        .load(item.getImageurls()[2])
                        .crossFade()
                        .placeholder(R.mipmap.ic_launcher)
                        .into((ImageView) helper.getView(R.id.news_image_url3));
            }

        helper.setText(R.id.book_info_textview_name,item.getTitle());
        helper.setText(R.id.book_info_textview_author, Html.fromHtml( "<font color=#E61A6B>"+item.getSource()+"</font>")+"  "+item.getPubDate());
    }
}
