package com.example.ygh.ttbd.home.tab;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;

import com.example.ygh.ttbd.BasePresenter;
import com.example.ygh.ttbd.BaseView;
import com.example.ygh.ttbd.data.get.News;

import java.util.List;

/**
 * Created by ygh on 2016/12/22.
 */

public interface HomeTabContract
{
    interface view extends BaseView<Presenter>
    {
        void showList();
        void reloadList();
        void showListClick(Uri uri);

        void showNewsList(List<News> list);
    }
    interface Presenter extends BasePresenter
    {
        void showList(RecyclerView recyclerView);
    }
}
