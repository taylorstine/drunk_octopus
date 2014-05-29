package com.tstine.spark.util;

import android.content.Context;

import com.tstine.spark.mixin.IProductApiService;

/**
 * Created by taylorstine on 5/29/14.
 */
public abstract class ApiServiceSingleton extends Singleton{

    static ApiServiceSingleton sInstance;

    public ApiServiceSingleton() {
        Singleton.register(ApiServiceSingleton.class, this);
    }

    public IProductApiService getProductApiService(Context context){
         return ((RestAdapterSingleton)Singleton.getInstance(RestAdapterSingleton.class))
             .getRestAdapter(context)
             .create(IProductApiService.class);
    }
}
