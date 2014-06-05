package com.tstine.spark.util;

import android.widget.AbsListView;

import org.androidannotations.annotations.EBean;

/**
 * Created by taylorstine on 6/5/14.
 */
@EBean
public class ScrollListenerDecorator implements AbsListView.OnScrollListener{

    private AbsListView.OnScrollListener mDecoratee;
    public ScrollListenerDecorator decorate(AbsListView.OnScrollListener decoratee){
        this.mDecoratee = decoratee;
        return this;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mDecoratee != null) {
            mDecoratee.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mDecoratee != null) {
            mDecoratee.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }
}
