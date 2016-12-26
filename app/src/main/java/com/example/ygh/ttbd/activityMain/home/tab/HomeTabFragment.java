package com.example.ygh.ttbd.activityMain.home.tab;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ygh.ttbd.R;
import com.example.ygh.ttbd.WrapContentHeightSwipeRefresh;
import com.example.ygh.ttbd.activityDetail.DetailActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

public class HomeTabFragment extends RxFragment implements HomeTabContract.view
{
    protected View                          rootView;
    protected RecyclerView                  mRecyclerView;
    protected WrapContentHeightSwipeRefresh mSwipeRefreshLayout;
    private   HomeTabContract.Presenter     mPresenter;
    private   View.OnClickListener          mOnClickListener;
    private   int                           mCurrentY;
    private   HomeTabRecycleViewAdapter     mHomeTabRecycleViewAdapter;

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
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mSwipeRefreshLayout = (WrapContentHeightSwipeRefresh) rootView
                .findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);
        mSwipeRefreshLayout
                .setOnRefreshListener(new WrapContentHeightSwipeRefresh.OnRefreshListener()
                {
                    @Override
                    public void onRefresh()
                    {
                        if (!mSwipeRefreshLayout.isRefreshing())
                        {
                            mPresenter.refresh();
                        }
                    }
                });
        final String title = getArguments().getString("title");
        mOnClickListener = createOnClickListenr(title);
        mHomeTabRecycleViewAdapter = new HomeTabRecycleViewAdapter(getContext(), null, title,
                                                                   mOnClickListener);
        mRecyclerView.setAdapter(mHomeTabRecycleViewAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            private int lastPosition;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                lastPosition = linearLayoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastPosition + 1 == mHomeTabRecycleViewAdapter
                        .getItemCount())
                {
                    mSwipeRefreshLayout.setRefreshing(true);
                    mPresenter.refresh();
                }
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void refreshList(List list)
    {
        showList(list);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showListClick(Uri uri)
    {

    }

    @Override
    public void showList(List list)
    {
        mHomeTabRecycleViewAdapter.setList(list);
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

    public int getCurrentY()
    {
        return mCurrentY;
    }

    public void setCurrentY(int currentY)
    {
        mCurrentY = currentY;
    }
}
