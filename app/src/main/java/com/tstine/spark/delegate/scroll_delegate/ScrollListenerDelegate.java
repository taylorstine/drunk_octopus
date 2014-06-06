package com.tstine.spark.delegate.scroll_delegate;

import android.widget.AbsListView;

import com.tstine.spark.mixin.StateMaintainer;
import com.tstine.spark.fetchstate.FetchState;
import com.tstine.spark.fetchstate.FetchStateWaiting;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Extends {@link com.tstine.spark.delegate.scroll_delegate.ScrollListenerDecorator} to
 * fetch data when the scroll state changes.  Delegates fetching to a {@link com.tstine.spark.fetchstate.FetchState}
 * object, which updates the internal state of this class based on if the data needs fetching
 * @see com.tstine.spark.mixin.StateMaintainer
 */
@EBean(scope = EBean.Scope.Singleton)
public class ScrollListenerDelegate extends  ScrollListenerDecorator implements StateMaintainer{

    @Bean(FetchStateWaiting.class) FetchState mState;

    @AfterInject
    protected void afterInejct(){
        mState.setStateMaintainer(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        super.onScrollStateChanged(view, scrollState);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mState != null){
            mState.fetch(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
        super.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
    }

    @Override
    public void setState(FetchState state) {
        this.mState = state;
    }

    @Override
    public FetchState getState() {
        return this.mState;
    }

}
