package com.tstine.spark.util;

import android.content.Context;

import com.tstine.spark.mixin.IProductApiService;

/**
 * Created by taylorstine on 5/29/14.
 */
public class ApiService{

    protected RestAdapterFactory mRestAdapterFactory;
    Context mContext;
    public ApiService(Context context, RestAdapterFactory restAdapterFactory) {
        mContext = context;
        mRestAdapterFactory = restAdapterFactory;
    }

    public IProductApiService getProductApiService(){
         return mRestAdapterFactory
             .makeRestAdapter()
             .create(IProductApiService.class);
    }
}
