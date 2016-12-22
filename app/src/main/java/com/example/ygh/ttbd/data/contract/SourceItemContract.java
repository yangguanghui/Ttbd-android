package com.example.ygh.ttbd.data.contract;

import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by ygh on 2016/12/20.
 */

public interface SourceItemContract
{
    interface LoadItemCallback
    {
        void onSuccess(Object item);

        void onError(Throwable throwable);
    }

    void loadItem(RxFragment fragment, final LoadItemCallback callback);
}
