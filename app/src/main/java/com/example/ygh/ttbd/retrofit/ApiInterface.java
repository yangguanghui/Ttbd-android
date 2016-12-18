package com.example.ygh.ttbd.retrofit;

import com.example.ygh.ttbd.ApiReturn;
import com.example.ygh.ttbd.ViewModel.VersionData;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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
}
