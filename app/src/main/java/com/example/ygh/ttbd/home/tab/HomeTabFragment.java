package com.example.ygh.ttbd.home.tab;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ygh.ttbd.R;
import com.example.ygh.ttbd.data.get.News;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

public class HomeTabFragment extends RxFragment implements HomeTabContract.view
{
    protected View         rootView;
    protected RecyclerView mNewsRecyclerView;
    private HomeTabContract.Presenter mPresenter;

    public static HomeTabFragment newInstance(String title)
    {
        Bundle arguments = new Bundle();
        arguments.putString("title", title);
        HomeTabFragment frament = new HomeTabFragment();
        frament.setArguments(arguments);
        return frament;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mPresenter = new HomeTabPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home_tab, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView)
    {
        mNewsRecyclerView = (RecyclerView) rootView.findViewById(R.id.newsRecyclerView);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showList()
    {

    }

    @Override
    public void reloadList()
    {

    }

    @Override
    public void showListClick(Uri uri)
    {

    }

    @Override
    public void showNewsList(List<News> list)
    {
        mNewsRecyclerView.setAdapter(new HomeTabNewsRecycleViewAdapter(getContext(),list));
    }

    @Override
    public void setPresenter(HomeTabContract.Presenter presenter)
    {
        mPresenter = presenter;
    }
}
