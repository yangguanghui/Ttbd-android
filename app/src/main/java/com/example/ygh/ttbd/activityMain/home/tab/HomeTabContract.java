package com.example.ygh.ttbd.activityMain.home.tab;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;

import com.example.ygh.ttbd.BasePresenter;
import com.example.ygh.ttbd.BaseView;

import java.util.List;

/**
 * Created by ygh on 2016/12/22.
 */

public interface HomeTabContract
{
    interface view extends BaseView<Presenter>
    {
        void showList(List list);
        void reloadList();
        void showListClick(Uri uri);

    }
    interface Presenter extends BasePresenter
    {
        void showList(RecyclerView recyclerView);
    }
}
