package com.example.ygh.ttbd.activityMain.home.tab;

import com.example.ygh.ttbd.data.contract.SourceListContract;
import com.example.ygh.ttbd.data.source.NewsSource;
import com.example.ygh.ttbd.data.source.NoticeSource;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by ygh on 2016/12/22.
 */

public class HomeTabRepository
{
    RxFragment mFragment;
    NewsSource mNewsSource;
    NoticeSource mNoticeSource;

    HomeTabRepository(RxFragment fragment)
    {
        mFragment = fragment;
        mNewsSource = NewsSource.getInstance();
        mNoticeSource = NoticeSource.getInstance();
    }

    void loadList(SourceListContract.LoadListCallback callback)
    {
        String title = mFragment.getArguments().getString("title");
        switch (title)
        {
            case "News":
                mNewsSource.loadList(1,mFragment, callback);
                break;
            case "Notice":
                mNoticeSource.loadList(1, mFragment, callback);
                break;
            case "Faculty":
                mNewsSource.loadList(1,mFragment, callback);
                break;
            default:
                break;
        }
    }
}
