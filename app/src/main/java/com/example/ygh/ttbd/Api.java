package com.example.ygh.ttbd;

import com.example.ygh.ttbd.ViewModel.VersionData;
import com.example.ygh.ttbd.retrofit.ApiInterface;
import com.example.ygh.ttbd.retrofit.ServiceGenerator;

import java.util.Map;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ygh on 2016/12/13.
 */

public class Api
{
    public static final Observable.Transformer simpleTransformer = new Observable.Transformer()
    {
        @Override
        public Object call(Object o)
        {
            return ((Observable) o)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static <S> Observable.Transformer<S, S> applySchedulers()
    {
        return (Observable.Transformer<S, S>) simpleTransformer;
    }

    public static <S> Observable<S> simpleApi(Observable<S> api,
                                              Observable.Transformer<S, S> trans)
    {
        return api.subscribeOn(Schedulers.io())
                  .compose(trans)
                  .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ApiReturn<Object>> installFirst(Map<String,String> installMap)
    {
        return ServiceGenerator.createService(ApiInterface.class).installFirst(installMap);
    }

    public static Observable<ApiReturn<Object>> installUpdate(Map<String, String> installMap)
    {
        return ServiceGenerator.createService(ApiInterface.class).installUpdate(installMap);
    }


    public static Observable<ApiReturn<VersionData>> getVersion()
    {
        return ServiceGenerator.createService(ApiInterface.class).getVersion();
    }

    public static Observable<ApiReturn<VersionData>> getVersion1()
    {
        return ServiceGenerator.createService(ApiInterface.class).getVersion1();
    }

}
