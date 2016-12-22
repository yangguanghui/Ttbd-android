package com.example.ygh.ttbd.data.contract;

import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

/**
 * Created by ygh on 2016/12/20.
 */

public interface SourceContract
{
    interface LoadListCallback
    {
        void onSuccess(List list);

        void onError(Throwable throwable);
    }

    void loadList(RxFragment fragment, final LoadListCallback callback);

    interface LoadItemCallback
    {
        void onSuccess(Object item);

        void onError(Throwable throwable);
    }

    void loadItem(RxFragment fragment, final LoadItemCallback callback);
}
