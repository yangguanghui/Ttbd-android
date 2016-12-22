package com.example.ygh.ttbd.data.contract;

import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

/**
 * Created by ygh on 2016/12/20.
 */

public interface SourceListContract
{
    interface LoadListCallback
    {
        void onSuccess(List list);

        void onError(Throwable throwable);
    }

    void loadList(RxFragment fragment, final LoadListCallback callback);
}
