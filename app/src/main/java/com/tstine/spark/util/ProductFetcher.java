package com.tstine.spark.util;

import android.content.Context;

import com.tstine.spark.mixin.IProductApiService;
import com.tstine.spark.model.Product;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by taylorstine on 5/30/14.
 */
public class ProductFetcher {
    public static final int DEFAULT_LIMIT = 20;
    public static final int DEFAULT_OFFSET = 0;
    private int mLimit;
    private int mOffset;
    private IProductApiService mProductApiService;
    private Context mContext;

    public interface IFetchedIt{
        void onFetch(List<Product> products);
        void onError(FetchError error);
    }

    public ProductFetcher(Context context, IProductApiService apiService){
        init(context, apiService, DEFAULT_LIMIT, DEFAULT_OFFSET);
    }

    public ProductFetcher(Context context, IProductApiService apiService, int limit, int offset){
        init(context, apiService, limit, offset);
    }

    private void init(Context context, IProductApiService apiService, int limit, int offset){
        this.mLimit = limit;
        this.mOffset = offset;
        mProductApiService = apiService;
        mContext = context;
    }

    public void fetch(IFetchedIt callback, int limit, int offset){
        doFetch(callback, limit, offset);
    }

    public void fetch(IFetchedIt callback){
        doFetch(callback, getLimit(), getOffset());
    }

    private void doFetch(final IFetchedIt callback, int limit, int offset){
        mProductApiService.getProducts(limit, offset, new Callback<List<Product>>() {
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



    public IProductApiService getProductApiService() {
        return mProductApiService;
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
