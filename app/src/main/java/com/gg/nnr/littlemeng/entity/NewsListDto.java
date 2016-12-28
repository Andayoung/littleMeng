package com.gg.nnr.littlemeng.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class NewsListDto implements Serializable {
    String title;//MERS”疫情影响 兰州游客赴韩游热情减退 新闻标题
    String link;//http://www.gs.xinhuanet.com/news/2015-06/06/c_1115531425.htm 新闻详情链接
    String pubDate;//2015-06-06 09:09:42/发布时间
    String source;//新华网 来源网站
    String desc;//疫情致兰州游客赴韩游热情减退受韩国中东/新闻简要描述
    String channelId;//5572a108b3cdc86cf39001cd 频道id
    String channelName;//国内焦点 频道名称
    String nid;//新闻对应的外网id
    String[] imageurls;//图片列表
    String content;//新闻正文,txt格式
    String html;//新闻正文,html格式

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String[] getImageurls() {
        return imageurls;
    }

    public void setImageurls(String[] imageurls) {
        this.imageurls = imageurls;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

}
