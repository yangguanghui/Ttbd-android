package com.example.ygh.ttbd.data.source;

import com.example.ygh.ttbd.Api;
import com.example.ygh.ttbd.data.ApiReturn;
import com.example.ygh.ttbd.data.contract.SourceListContract;
import com.example.ygh.ttbd.data.get.News;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by ygh on 2016/12/20.
 */

public class NewsSource implements SourceListContract
{
    private static NewsSource   INSTANCE;
    public static  List<News> mNews;

    private NewsSource()
    {

    }
    public static NewsSource getInstance()
    {
        if(null == INSTANCE)
        {
            INSTANCE = new NewsSource();
        }
        return INSTANCE;
    }

    @Override
    public void loadList(int page, RxFragment fragment, final LoadListCallback callback)
    {
        Api.simpleApi(Api.getNews(page), fragment.<ApiReturn<List<News>>>bindToLifecycle())
           .subscribe(new Action1<ApiReturn<List<News>>>()
           {
               @Override
               public void call(ApiReturn<List<News>> listApiReturn)
               {
                   mNews = listApiReturn.data;
                   callback.onSuccess(mNews);
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
