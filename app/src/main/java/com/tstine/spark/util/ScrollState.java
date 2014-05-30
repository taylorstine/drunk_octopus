package com.tstine.spark.util;

import android.widget.AbsListView;

import com.tstine.spark.mixin.StateMaintainer;

/**
 * Created by taylorstine on 5/30/14.
 */
public abstract class ScrollState {

    protected ProductFetcher mFetcher;
    protected GridAdapter mGridAdapter;
    protected StateMaintainer mStateMaintainer;

    public ScrollState(ProductFetcher fetcher, GridAdapter adapter, StateMaintainer maintainer){
        this.mFetcher = fetcher;
        this.mGridAdapter = adapter;
        this.mStateMaintainer = maintainer;
    }
    public abstract void fetch(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
}
