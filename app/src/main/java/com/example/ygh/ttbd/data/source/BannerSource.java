package com.example.ygh.ttbd.data.source;

import com.example.ygh.ttbd.Api;
import com.example.ygh.ttbd.data.ApiReturn;
import com.example.ygh.ttbd.data.contract.SourceListContract;
import com.example.ygh.ttbd.data.get.Banner;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by ygh on 2016/12/20.
 */

public class BannerSource implements SourceListContract
{
    private static BannerSource INSTANCE;
    public static List<Banner> mBanner;

    public static BannerSource getInstance()
    {
        if(null == INSTANCE)
        {
            INSTANCE = new BannerSource();
        }
        return INSTANCE;
    }

    @Override
    public void loadList(RxFragment fragment, final LoadListCallback callback)
    {
        Api.simpleApi(Api.getBanners(), fragment.<ApiReturn<List<Banner>>>bindToLifecycle())
           .subscribe(new Action1<ApiReturn<List<Banner>>>()
           {
               @Override
               public void call(ApiReturn<List<Banner>> listApiReturn)
               {
                   mBanner = listApiReturn.data;
                   callback.onSuccess(mBanner);
               }
           }, new Action1<Throwable>()
           {
               @Override
               public void call(Throwable throwable)
               {
                   callback.onError(throwable);
               }
           });
    }
}
