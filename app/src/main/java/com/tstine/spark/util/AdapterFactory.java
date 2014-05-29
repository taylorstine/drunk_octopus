package com.tstine.spark.util;

import android.content.Context;
import android.widget.ListAdapter;

import com.google.gson.Gson;
import com.tstine.spark.mixin.IProductApiService;

/**
 * Created by taylorstine on 5/29/14.
 */
public class AdapterFactory extends Singleton {
    static AdapterFactory sInstance;
    protected AdapterFactory() {
        Singleton.register(AdapterFactory.class, this);
    }

    public ListAdapter makeAdapter(Context context){
        return new GridAdapter(context, ((ApiServiceSingleton)Singleton.getInstance(ApiServiceSingleton.class)).getProductApiService(context));
    }

}
