package com.example.ygh.ttbd;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by ygh on 2016/12/21.
 */

public class ImageHolderView implements Holder<String>
{
    private ImageView mImageView;
    @Override
    public View createView(Context context)
    {
        mImageView = new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data)
    {
//        mImageView.setImageResource(R.drawable.ic_default_adimage);
        ImageLoader.getInstance().displayImage(data,mImageView);
    }
}
