package com.example.ygh.ttbd.activityMain.home.tab;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ygh.ttbd.data.contract.SourceListContract;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

/**
 * Created by ygh on 2016/12/22.
 */

public class HomeTabPresenter implements HomeTabContract.Presenter
{
    HomeTabContract.view mHomeTabView;
    RxFragment           mFragment;
    HomeTabRepository    mHomeTabRepository;

    public HomeTabPresenter(HomeTabContract.view view)
    {
        mHomeTabView = view;
        mFragment = (RxFragment) view;
        mHomeTabRepository = new HomeTabRepository(mFragment);
    }

    @Override
    public void showList(RecyclerView recyclerView)
    {

    }

    public SourceListContract.LoadListCallback mLoadListCallback = new SourceListContract
            .LoadListCallback()
    {
        @Override
        public void onSuccess(List list)
        {
            mHomeTabView.showList(list);
        }

        @Override
        public void onError(Throwable throwable)
        {
            Log.e(this.toString(), "onError: " + throwable.getMessage());
        }
    };

    @Override
    public void start()
    {
        mHomeTabRepository.loadList(mLoadListCallback);
    }

}
