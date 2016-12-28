package com.gg.nnr.littlemeng.data;

import com.gg.nnr.littlemeng.Retrofit.ApiException;
import com.gg.nnr.littlemeng.Retrofit.RetrofitUtils;
import com.gg.nnr.littlemeng.api.APIService;
import com.gg.nnr.littlemeng.api.CacheProviders;
import com.gg.nnr.littlemeng.constant.Constant;
import com.gg.nnr.littlemeng.entity.HttpResult;
import com.gg.nnr.littlemeng.entity.NewsListDto;
import com.gg.nnr.littlemeng.util.FileUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import io.rx_cache.internal.RxCache;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class HttpData extends RetrofitUtils {

    private static File cacheDirectory = FileUtil.getcacheDirectory();
    private static final CacheProviders providers = new RxCache.Builder()
            .persistence(cacheDirectory)
            .using(CacheProviders.class);

    protected static final APIService service = getRetrofit().create(APIService.class);


    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpData INSTANCE = new HttpData();
    }

    //获取单例
    public static HttpData getInstance() {
        return SingletonHolder.INSTANCE;
    }

  /*  //Get请求  视频列表
    public void verfacationCodeGetCache(Observer<List<VideoListDto>> observer) {
        Observable observable=service.getVideoList().map(new HttpResultFunc<List<VideoListDto>>());
        Observable observableCahce=providers.getVideoList(observable,new DynamicKey("视频列表"),new EvictDynamicKey(false)).map(new HttpResultFuncCcche<List<VideoListDto>>());
        setSubscribe(observableCahce,observer);
    }*/


    public void HttpDataToNewsList(String channelName, String page, Observer<List<NewsListDto>> observer) {
        Observable observable = service.getNewsList(Constant.APP_ID, Constant.API_KEY, channelName, page, "1")
                .map(new HttpResultFunc<List<NewsListDto>>());
        Observable observableCache = providers.getNewsList(observable, new DynamicKey("news" + channelName + page), new EvictDynamicKey(true))
                .map(new HttpResultFuncCache<List<NewsListDto>>());
        setSubscribe(observableCache, observer);
    }

    /**
     * 插入观察者
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult, List<NewsListDto>> {
        @Override
        public List<NewsListDto> call(HttpResult httpResult) {

            List<NewsListDto> listDtos = null;
            if (httpResult.getShowapi_res_code() != 0) {
                throw new ApiException(httpResult);
            }
            JSONObject body = new JSONObject((Map) httpResult.getShowapi_res_body());
            try {
                JSONObject pagebean = body.getJSONObject("pagebean");
                JSONArray contentlist = pagebean.getJSONArray("contentlist");
                listDtos = new ArrayList<>();
                for (int i = 0; i < contentlist.length(); i++) {
                    NewsListDto newsListDto = new NewsListDto();
                    newsListDto.setTitle(contentlist.getJSONObject(i).getString("title"));
                    newsListDto.setSource(contentlist.getJSONObject(i).getString("source"));
                    newsListDto.setDesc(contentlist.getJSONObject(i).getString("desc"));
                    newsListDto.setLink(contentlist.getJSONObject(i).getString("link"));
                    newsListDto.setPubDate(contentlist.getJSONObject(i).getString("pubDate"));
                    JSONArray imagelist = contentlist.getJSONObject(i).getJSONArray("imageurls");
                    int imageLength = imagelist.length();
                    String[] images = new String[imageLength];
                    for (int j = 0; j < imageLength; j++) {
                        images[j] = imagelist.getJSONObject(j).getString("url");
                    }
                    newsListDto.setImageurls(images);
                    listDtos.add(newsListDto);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return listDtos;
        }
    }

    /**
     * 用来统一处理RxCacha的结果
     */
    private class HttpResultFuncCache<T> implements Func1<Reply<T>, T> {

        @Override
        public T call(Reply<T> httpResult) {
            return httpResult.getData();
        }
    }
}
