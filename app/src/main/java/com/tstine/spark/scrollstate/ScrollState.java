package com.tstine.spark.scrollstate;

import android.widget.AbsListView;

import com.tstine.spark.mixin.StateMaintainer;
import com.tstine.spark.rest.Fetcher;
import com.tstine.spark.util.GridAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by taylorstine on 5/30/14.
 */
@EBean
public abstract class ScrollState {

    @Bean
    Fetcher mFetcher;
    @Bean
    GridAdapter mGridAdapter;
    protected StateMaintainer mStateMaintainer;

    public abstract void fetch(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);

    public StateMaintainer getStateMaintainer() {
        return mStateMaintainer;
    }

    public void setStateMaintainer(StateMaintainer mStateMaintainer) {
        this.mStateMaintainer = mStateMaintainer;
    }
}
