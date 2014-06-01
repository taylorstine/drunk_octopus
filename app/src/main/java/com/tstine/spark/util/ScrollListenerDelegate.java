package com.tstine.spark.util;

import android.widget.AbsListView;

import com.tstine.spark.mixin.StateMaintainer;
import com.tstine.spark.scrollstate.ScrollState;
import com.tstine.spark.scrollstate.ScrollStateWaiting;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by taylorstine on 6/1/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class ScrollListenerDelegate implements AbsListView.OnScrollListener, StateMaintainer{

    @Bean(ScrollStateWaiting.class)
    ScrollState mState;

    @AfterInject
    protected void afterInejct(){
        mState.setStateMaintainer(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mState != null){
            mState.fetch(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    @Override
    public void setState(ScrollState state) {
        this.mState = state;
    }
}
