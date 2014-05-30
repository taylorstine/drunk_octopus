package com.tstine.spark.util;

import android.widget.AbsListView;

import com.tstine.spark.mixin.StateMaintainer;

/**
 * Created by taylorstine on 5/30/14.
 */
public class FetchingState extends  ScrollState{
    public FetchingState(ProductFetcher fetcher, GridAdapter adapter, StateMaintainer maintainer) {
        super(fetcher, adapter, maintainer);
    }

    @Override
    public void fetch(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
