package com.tstine.spark.fetchstate;

import android.widget.AbsListView;

import org.androidannotations.annotations.EBean;

/**
 * Created by taylorstine on 5/30/14.
 */
@EBean
public class FetchStateFetching extends FetchState {

    @Override
    public void fetch(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }
}
