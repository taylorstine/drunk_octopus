package com.tstine.spark.util;

import android.content.Context;
import android.widget.AbsListView;

import com.tstine.spark.mixin.StateMaintainer;
import com.tstine.spark.model.Product;

import java.util.List;

/**
 * Created by taylorstine on 5/29/14.
 */
public class ProductDataMediator implements AbsListView.OnScrollListener, StateMaintainer{

    private Context mContext;
    private AbsListView mListView;
    private ProductFetcher mFetcher;
    private GridAdapter mAdapter;
    private ScrollState mState;

    public ProductDataMediator(Context context, AbsListView view, ProductFetcher fetcher, GridAdapter adapter){
        mListView = view;
        mContext = context;
        mFetcher = fetcher;
        mAdapter = adapter;
        mListView.setOnScrollListener(this);
        mState = new ScrollStateWaiting(mFetcher, mAdapter, this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mState.fetch(view, firstVisibleItem, visibleItemCount, totalItemCount);
    }


    @Override
    public void setState(ScrollState state) {
        this.mState = state;
    }
}
