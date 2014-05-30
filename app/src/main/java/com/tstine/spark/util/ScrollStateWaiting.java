package com.tstine.spark.util;

import android.widget.AbsListView;

import com.tstine.spark.mixin.StateMaintainer;
import com.tstine.spark.model.Product;

import java.util.List;

/**
 * Created by taylorstine on 5/30/14.
 */
public class ScrollStateWaiting extends ScrollState{

    public ScrollStateWaiting(ProductFetcher fetcher, GridAdapter adapter, StateMaintainer maintainer) {
        super(fetcher, adapter, maintainer);
    }

    @Override
    public void fetch(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount){
        if (needsToFetchMoreProducts(firstVisibleItem, visibleItemCount, totalItemCount)){
            mStateMaintainer.setState(new FetchingState(mFetcher, mGridAdapter, mStateMaintainer));
            final ScrollState state = this;
            mFetcher.fetch(new ProductFetcher.IFetchedIt() {
                @Override
                public void onFetch(List<Product> products) {
                    mGridAdapter.appendItems(products);
                    mGridAdapter.notifyDataSetChanged();
                    mStateMaintainer.setState(state);
                }

                @Override
                public void onError(FetchError error){
                    mStateMaintainer.setState(state);
                }
            });
        }
    }



    public boolean needsToFetchMoreProducts(int firstVisibleItem, int visibleItemCount, int totalItemCount){
        int lastVisibleItem =firstVisibleItem + visibleItemCount;
        int itemsNotSeen = totalItemCount - lastVisibleItem;
        return itemsNotSeen < getExtraItemPadding();
    }

    public int getExtraItemPadding(){
        return 10;
    }
}
