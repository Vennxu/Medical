package com.example.administrator.medical.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.example.administrator.medical.command.NewsCommandUrl;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2015/7/17.
 */
public class NewImageDisplay {

    private Context mContext;
    private static NewImageDisplay mImageDisplay;

    public static synchronized void initInstance(Context context) {
        if (mImageDisplay == null) {
            mImageDisplay = new NewImageDisplay(context.getApplicationContext());
        }
    }

    public static NewImageDisplay getInstance(Context context) {
        if (mImageDisplay == null) {
            initInstance(context);
        }
        return mImageDisplay;
    }

    public NewImageDisplay(Context context) {
        mContext = context;
    }

    public void loadImageUrl(String url, ImageView imageView) {
        Picasso.with(mContext).load(NewsCommandUrl.NEWS_IMAGE_LOAD_URL + url).into(imageView);
    }

    public void loadImageUrl(String url, ImageView imageView, int drawable) {
        Picasso.with(mContext).load(NewsCommandUrl.NEWS_IMAGE_LOAD_URL + url).error(drawable).into(imageView);
    }

}
