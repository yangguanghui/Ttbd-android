package com.example.ygh.ttbd.home;

import android.net.Uri;

import com.example.ygh.ttbd.BasePresenter;
import com.example.ygh.ttbd.BaseView;

import java.util.List;

/**
 * Created by ygh on 2016/12/20.
 */

public interface HomeContract
{
    interface view extends BaseView<Presenter>
    {
        void showBanner(List<String> bannerUrls);
        void reloadPage();
        void showSearchResult(String keyword);
        void showBannerClickPage(Uri uri);
    }
    interface Presenter extends BasePresenter
    {
    }
}
