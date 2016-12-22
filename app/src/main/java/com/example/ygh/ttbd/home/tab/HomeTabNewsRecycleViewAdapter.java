package com.example.ygh.ttbd.home.tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ygh.ttbd.R;
import com.example.ygh.ttbd.data.get.News;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import static com.example.ygh.ttbd.R.id.imageView;

/**
 * Created by ygh on 2016/12/21.
 */

public class HomeTabNewsRecycleViewAdapter extends RecyclerView.Adapter
{
    private final LayoutInflater mLayoutInflater;
    private final Context        mContext;
    private       List<News>     mNewsList;

    public HomeTabNewsRecycleViewAdapter(Context context, List<News> newsList)
    {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mNewsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new NewsViewHolder(
                mLayoutInflater.inflate(R.layout.list_news_home_tab, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        NewsViewHolder newsHolder = (NewsViewHolder) holder;
        News item = mNewsList.get(position);
        newsHolder.mTitleTextView.setText(item.getTitle());
        newsHolder.mDateTextView.setText(item.getCreate_at());
        newsHolder.mContentTextView.setText(item.getBrief());
        ImageLoader.getInstance().displayImage(item.getImage_url(), newsHolder.mImageView);
    }

    @Override
    public int getItemCount()
    {
        return mNewsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder
    {

        private final ImageView mImageView;
        private final TextView mTitleTextView;
        private final TextView mContentTextView;
        private final TextView mDateTextView;

        public NewsViewHolder(View itemView)
        {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(imageView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            mContentTextView = (TextView) itemView.findViewById(R.id.contentTextView);
            mDateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
        }
    }
}
