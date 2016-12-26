package com.example.ygh.ttbd.data.source;

import com.example.ygh.ttbd.Api;
import com.example.ygh.ttbd.data.ApiReturn;
import com.example.ygh.ttbd.data.contract.SourceListContract;
import com.example.ygh.ttbd.data.get.Notice;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by ygh on 2016/12/20.
 */

public class NoticeSource implements SourceListContract
{
    private static NoticeSource INSTANCE;
    public static  List<Notice> mNotice;

    private NoticeSource()
    {

    }
    public static NoticeSource getInstance()
    {
        if(null == INSTANCE)
        {
            INSTANCE = new NoticeSource();
        }
        return INSTANCE;
    }

    @Override
    public void loadList(int page, RxFragment fragment, final LoadListCallback callback)
    {
        Api.simpleApi(Api.getNotice(), fragment.<ApiReturn<List<Notice>>>bindToLifecycle())
           .subscribe(new Action1<ApiReturn<List<Notice>>>()
           {
               @Override
               public void call(ApiReturn<List<Notice>> listApiReturn)
               {
                   mNotice = listApiReturn.data;
                   callback.onSuccess(mNotice);
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
