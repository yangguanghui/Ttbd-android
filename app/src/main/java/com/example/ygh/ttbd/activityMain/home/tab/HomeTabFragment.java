package com.example.ygh.ttbd.activityMain.home.tab;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ygh.ttbd.R;
import com.example.ygh.ttbd.activityDetail.DetailActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

public class HomeTabFragment extends RxFragment implements HomeTabContract.view
{
    protected View                      rootView;
    protected RecyclerView              mRecyclerView;
    private   HomeTabContract.Presenter mPresenter;
    private   View.OnClickListener      mOnClickListener;

    public static HomeTabFragment newInstance(String title)
    {
        Bundle arguments = new Bundle();
        arguments.putString("title", title);
        HomeTabFragment fragment = new HomeTabFragment();
        fragment.setArguments(arguments);
        return fragment;
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
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mPresenter.start();
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
    public void showList(List list)
    {
        final String title = getArguments().getString("title");
        mOnClickListener = createOnClickListenr(title);
        mRecyclerView.setAdapter(new HomeTabRecycleViewAdapter(getContext(), list, title, mOnClickListener));
    }

    private View.OnClickListener createOnClickListenr(final String title)
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
                Log.e(this.toString(),
                      "onClick: Start DetailActivity with title < " + title + " >");
            }
        };
    }

    @Override
    public void setPresenter(HomeTabContract.Presenter presenter)
    {
        mPresenter = presenter;
    }
}
