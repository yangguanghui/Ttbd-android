package com.example.ygh.ttbd.home;

import com.example.ygh.ttbd.data.contract.SourceListContract;
import com.example.ygh.ttbd.data.source.BannerSource;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by ygh on 2016/12/20.
 */

public class HomeRepository
{
    RxFragment mFrament;

    BannerSource mBannerSource;

    public HomeRepository(RxFragment frament)
    {
        mFrament = frament;
        mBannerSource = BannerSource.getInstance();
    }

    void loadBanners(SourceListContract.LoadListCallback callback)
    {
        mBannerSource.loadList(mFrament, callback);
    }
}
