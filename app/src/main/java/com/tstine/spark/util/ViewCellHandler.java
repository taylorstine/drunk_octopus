package com.tstine.spark.util;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.tstine.spark.model.Product;

import org.androidannotations.annotations.EBean;

/**
 * Created by taylorstine on 6/2/14.
 */
@EBean
public abstract class ViewCellHandler {

    private int mImageWidth, mImageHeight;
    private float mImageAspect;
    private Drawable mPlaceHolder;
    private ImageView mImageView;



    public int getImageWidth() {
        return mImageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.mImageWidth = imageWidth;
    }

    public int getImageHeight() {
        return mImageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.mImageHeight = imageHeight;
    }

    public float getImageAspect() {
        return mImageAspect;
    }

    public void setImageAspect(float imageAspect) {
        this.mImageAspect = imageAspect;
    }

    public Drawable getmPlaceHolder() {
        return mPlaceHolder;
    }

    public void setmPlaceHolder(Drawable mPlaceHolder) {
        this.mPlaceHolder = mPlaceHolder;
    }

    public ImageView getmImageView() {
        return mImageView;
    }

    public void setmImageView(ImageView mImageView) {
        this.mImageView = mImageView;
    }
}
