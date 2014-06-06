package com.tstine.spark.fetchstate;

import android.app.Activity;
import android.widget.AbsListView;

import com.tstine.spark.R;
import com.tstine.spark.model.Product;
import com.tstine.spark.util.FetchError;
import com.tstine.spark.util.Logger;
import com.tstine.spark.rest.Fetcher;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.Trace;

import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;

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
                    mGridAdapter.appendItems(products);
                    mGridAdapter.notifyDataSetChanged();
                    mStateMaintainer.setState(state);
                }

                @Override
                public void onError(FetchError error) {
                    mStateMaintainer.setState(state);
                    Crouton.make(mActivity, mActivity.getLayoutInflater().inflate(R.layout.crouton_error, null)).show();
                    Logger.log("Got fetchProducts error: " + error.getReason());
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
