package com.gg.nnr.littlemeng.api;

import com.gg.nnr.littlemeng.entity.HttpResult;
import com.gg.nnr.littlemeng.entity.NewsListDto;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * API接口
 * 因为使用RxCache作为缓存策略 所以这里不需要写缓存信息
 */
public interface APIService {

//    //获取视频地址
//    @GET("video/getVideoList")
//    Observable<HttpResult<List<VideoListDto>>> getVideoList();



    @GET("109-35")
    Observable<HttpResult> getNewsList(@Query("showapi_appid") String appid, @Query("showapi_sign") String key
            ,@Query("channelName") String channelName,@Query("page") String page,@Query("needContent") String needContent);


}
