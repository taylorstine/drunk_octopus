package com.tstine.spark.fetchstate;

import android.widget.AbsListView;

import com.tstine.spark.model.Product;
import com.tstine.spark.rest.FetchError;
import com.tstine.spark.rest.Fetcher;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.Trace;

import java.util.List;

/**
 * Created by taylorstine on 5/30/14.
 */
@EBean
public class FetchStateWaiting extends FetchState {

    @Trace
    @Override
    public void fetch(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount){
        if (needsToFetchMoreProducts(firstVisibleItem, visibleItemCount, totalItemCount)){
            mStateMaintainer.setState(new FetchStateFetching());
            final FetchState state = this;
            mFetcher.fetchProducts(new Fetcher.IFetchedIt() {
                @Override
                public void onFetch(List<Product> products) {
                    mStateMaintainer.setState(state);
                    if (products == null || products.isEmpty()){
                        onError(null);
                    }
                    mGridAdapter.appendItems(products);
                    mGridAdapter.notifyDataSetChanged();
                }

                @Override
                public void onError(FetchError error) {
                    mStateMaintainer.setState(state);
                    getErrorHandler().handleFetchProductError(error);
                }
            });
        }
    }



    public boolean needsToFetchMoreProducts(int firstVisibleItem, int visibleItemCount, int totalItemCount){
        int lastVisibleItem = firstVisibleItem + visibleItemCount;
        int itemsNotSeen = totalItemCount - lastVisibleItem;
        return itemsNotSeen < getExtraItemPadding();
    }

    public int getExtraItemPadding(){
        return 10;
    }
}
