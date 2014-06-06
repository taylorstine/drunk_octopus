package com.tstine.spark.util;

import android.app.Activity;
import android.widget.AbsListView;

import com.tstine.spark.mixin.ScrollStateSubject;
import com.tstine.spark.mixin.StateMaintainer;
import com.tstine.spark.fetchstate.FetchState;
import com.tstine.spark.fetchstate.FetchStateWaiting;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taylorstine on 6/1/14.
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
