package com.example.ygh.ttbd.home.tab;

import com.example.ygh.ttbd.data.contract.SourceListContract;
import com.example.ygh.ttbd.data.source.NewsSource;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by ygh on 2016/12/22.
 */

public class HomeTabRepository
{
    RxFragment mFragment;
    NewsSource mNewsSource;

    HomeTabRepository(RxFragment fragment)
    {
        mFragment = fragment;
        mNewsSource = NewsSource.getInstance();
    }

    void loadNewsList(SourceListContract.LoadListCallback callback)
    {
        mNewsSource.loadList(mFragment, callback);
    }
}
