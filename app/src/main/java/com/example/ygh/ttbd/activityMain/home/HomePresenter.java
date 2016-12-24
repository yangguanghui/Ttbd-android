package com.example.ygh.ttbd.activityMain.home;

import android.util.Log;

import com.example.ygh.ttbd.data.contract.SourceListContract;
import com.example.ygh.ttbd.data.get.Banner;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ygh on 2016/12/20.
 */

public class HomePresenter implements HomeContract.Presenter
{
    private HomeContract.view mHomeView;
    private HomeRepository    mHomeRepository;
    private RxFragment        mFragment;

    public HomePresenter(HomeContract.view homeView)
    {
        mHomeView = homeView;
        mFragment = (RxFragment) homeView;
        mHomeRepository = new HomeRepository(mFragment);
    }

    @Override
    public void start()
    {
        mHomeRepository.loadBanners(mLoadBannersCallback);
    }

    private SourceListContract.LoadListCallback mLoadBannersCallback = new SourceListContract.LoadListCallback()
    {
        @Override
        public void onSuccess(List bannerList)
        {
            List<String> bannerUrls = new ArrayList<>();
            for (Object banner : bannerList)
            {
                bannerUrls.add(((Banner)banner).getImage_url());
            }
            mHomeView.showBanner(bannerUrls);
        }

        @Override
        public void onError(Throwable throwable)
        {
            Log.e(this.toString(), "onError: " + throwable.getMessage());
        }
    };

}
