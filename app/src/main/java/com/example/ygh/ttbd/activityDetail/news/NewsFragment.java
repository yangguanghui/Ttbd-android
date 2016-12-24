package com.example.ygh.ttbd.activityDetail.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ygh.ttbd.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewsFragment extends Fragment
{

    protected View     rootView;
    protected TextView mTitle;
    protected TextView mContent;

    public NewsFragment()
    {
    }

    public static NewsFragment newInstance(int id)
    {

        Bundle args = new Bundle();
        args.putInt("id", id);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView)
    {
        mTitle = (TextView) rootView.findViewById(R.id.title);
        mContent = (TextView) rootView.findViewById(R.id.content);
    }

    public void showDetail()
    {
        mTitle.setText("Title");
        mContent.setText("Content");
    }
}
