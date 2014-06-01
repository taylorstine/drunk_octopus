package com.tstine.spark.rest;

import com.tstine.spark.model.Product;
import com.tstine.spark.util.FetchError;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.Trace;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by taylorstine on 5/30/14.
 */
@EBean
public class Fetcher {
    private int mLimit = 20;
    private int mOffset = 0;

    @Bean protected ApiService mApiService;

    public interface IFetchedIt{
        void onFetch(List<Product> products);
        void onError(FetchError error);
    }

    public void fetchProducts(IFetchedIt callback, int limit, int offset){
        doFetchProducts(callback, limit, offset);
    }

    public void fetchProducts(IFetchedIt callback){
        doFetchProducts(callback, getLimit(), getOffset());
    }

    @Trace
    private void doFetchProducts(final IFetchedIt callback, int limit, int offset){
        mApiService.getProductApiService().getProducts(limit, offset, new Callback<List<Product>>() {
            @Override
            public void success(List<Product> products, Response response) {
                callback.onFetch(products);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onError(new FetchError(error));
            }
        });

    }

    public int getLimit() {
        return mLimit;
    }

    public void setLimit(int mLimit) {
        this.mLimit = mLimit;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int mOffset) {
        this.mOffset = mOffset;
    }
}
