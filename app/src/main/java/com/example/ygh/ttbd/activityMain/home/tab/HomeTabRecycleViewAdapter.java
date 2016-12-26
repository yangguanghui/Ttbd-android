package com.example.ygh.ttbd.activityMain.home.tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ygh.ttbd.R;
import com.example.ygh.ttbd.data.get.News;
import com.example.ygh.ttbd.data.get.Notice;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import static com.example.ygh.ttbd.R.id.imageView;

/**
 * Created by ygh on 2016/12/21.
 */

public class HomeTabRecycleViewAdapter extends RecyclerView.Adapter
{
    private final LayoutInflater       mLayoutInflater;
    private final Context              mContext;
    private       List                 mList;
    private       String               mTitle;
    private       View.OnClickListener mOnClickListenner;

    public HomeTabRecycleViewAdapter(Context context, List list, String title,
                                     View.OnClickListener onClickListener)
    {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mList = list;
        mTitle = title;
        mOnClickListenner = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ViewHolder viewHolder = null;
        switch (mTitle)
        {
            case "News":
                viewHolder = new NewsViewHolder(
                        mLayoutInflater.inflate(R.layout.list_news_home_tab, parent, false));
                break;
            case "Notice":
                viewHolder = new NoticeViewHolder(
                        mLayoutInflater.inflate(R.layout.list_news_home_tab, parent, false));
                break;
            case "Faculty":
                viewHolder = new NewsViewHolder(
                        mLayoutInflater.inflate(R.layout.list_news_home_tab, parent, false));
                break;
            default:
                Log.e(this.toString(), "onCreateViewHolder: Title missing");
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        switch (mTitle)
        {
            case "News":
                bindNews((NewsViewHolder) holder, (News) mList.get(position));
                break;
            case "Notice":
                bindNotice((NoticeViewHolder) holder, (Notice) mList.get(position));
                break;
            case "Faculty":
                bindNews((NewsViewHolder) holder, (News) mList.get(position));
                break;
        }
    }

    private void bindNews(NewsViewHolder holder, News item)
    {
        holder.mTitleTextView.setText(item.getTitle());
        holder.mDateTextView.setText(item.getCreate_at());
        holder.mContentTextView.setText(item.getBrief());
        ImageLoader.getInstance().displayImage(item.getImage_url(), holder.mImageView);
        holder.itemView.setOnClickListener(mOnClickListenner);
    }

    private void bindNotice(NoticeViewHolder holder, Notice item)
    {
        holder.mTitleTextView.setText(item.getTitle());
        holder.mDateTextView.setText(item.getCreate_at());
        holder.mContentTextView.setText(item.getBrief());
        ImageLoader.getInstance().displayImage(item.getImage_url(), holder.mImageView);
        holder.itemView.setOnClickListener(mOnClickListenner);
    }

    @Override
    public int getItemCount()
    {
        return null == mList ? 0 : mList.size();
    }

    public void setList(List list)
    {
        if (mList != null)
        {
            mList.clear();
        }
        mList = list;
        notifyDataSetChanged();
    }

    public static class NewsViewHolder extends ViewHolder
    {

        private final ImageView mImageView;
        private final TextView  mTitleTextView;
        private final TextView  mContentTextView;
        private final TextView  mDateTextView;

        public NewsViewHolder(View itemView)
        {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(imageView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            mContentTextView = (TextView) itemView.findViewById(R.id.contentTextView);
            mDateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
        }
    }

    public static class NoticeViewHolder extends ViewHolder
    {

        private final ImageView mImageView;
        private final TextView  mTitleTextView;
        private final TextView  mContentTextView;
        private final TextView  mDateTextView;

        public NoticeViewHolder(View itemView)
        {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(imageView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            mContentTextView = (TextView) itemView.findViewById(R.id.contentTextView);
            mDateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
        }
    }
}
