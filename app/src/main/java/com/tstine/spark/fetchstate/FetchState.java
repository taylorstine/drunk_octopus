package com.tstine.spark.fetchstate;

import android.widget.AbsListView;

import com.tstine.spark.mixin.StateMaintainer;
import com.tstine.spark.rest.Fetcher;
import com.tstine.spark.util.ErrorHandler;
import com.tstine.spark.adapter.GridAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by taylorstine on 5/30/14.
 */
@EBean
public abstract class FetchState {

    @Bean Fetcher mFetcher;
    @Bean  ErrorHandler mErrorHandler;
    @Bean GridAdapter mGridAdapter;

    protected StateMaintainer mStateMaintainer;

    public abstract void fetch(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);

    public StateMaintainer getStateMaintainer() {
        return mStateMaintainer;
    }

    public void setStateMaintainer(StateMaintainer mStateMaintainer) {
        this.mStateMaintainer = mStateMaintainer;
    }

    public ErrorHandler getErrorHandler(){
        return this.mErrorHandler;
    }
}
