package com.example.ygh.ttbd;

import com.example.ygh.ttbd.data.ApiReturn;
import com.example.ygh.ttbd.data.ViewModel.VersionData;
import com.example.ygh.ttbd.data.get.Banner;
import com.example.ygh.ttbd.data.get.News;
import com.example.ygh.ttbd.data.get.Notice;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ygh on 2016/12/12.
 */

public interface ApiInterface
{
    @GET("versions/1")
    Observable<ApiReturn<VersionData>> getVersion();

    @GET("versions/1")
    Observable<ApiReturn<VersionData>> getVersion1();

//    @Headers("Cookie: advanced-api=37ec0886fcdsod1qum3lpvb8g4; _csrf-api=bb5327325edd020b981af92afaaee0e0675861a6daf85d9d5f951f617a28e19ca%3A2%3A%7Bi%3A0%3Bs%3A9%3A%22_csrf-api%22%3Bi%3A1%3Bs%3A32%3A%22qiQPP8_nqA2lCT6jVjFAzcImuEfySP9d%22%3B%7D; XDEBUG_SESSION=netbeans-xdebug")
    @FormUrlEncoded
    @POST("install/first")
    Observable<ApiReturn<Object>> installFirst(@FieldMap Map<String,String> versionData);

    @FormUrlEncoded
    @POST("install/update")
    Observable<ApiReturn<Object>> installUpdate(@FieldMap Map<String,String> versionData);

    @GET("banner")
    Observable<ApiReturn<List<Banner>>> getBanners();

    @GET("news")
    Observable<ApiReturn<List<News>>> getNews(@Query("page") int page);

    @GET("notice")
    Observable<ApiReturn<List<Notice>>> getNotice();
}
