package com.tstine.spark.delegate.scroll_delegate;

import android.widget.AbsListView;

import org.androidannotations.annotations.EBean;

/**
 * Class the provides decoration to a scroll listener.  This allows multiple scroll listeners
 * to be attached by calling {@link #decorate(android.widget.AbsListView.OnScrollListener)} on
 * an instance of this class
 */
@EBean
public class ScrollListenerDecorator implements AbsListView.OnScrollListener{

    /**
     * The next object in the chain that the requests are forwarded to
     */
    private AbsListView.OnScrollListener mDecoratee;

    /**
     * Adds an object to forward scroll data to
     * @param decoratee
     * @return
     */
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
