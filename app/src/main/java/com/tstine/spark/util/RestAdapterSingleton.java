package com.tstine.spark.util;

import android.content.Context;

import com.tstine.spark.R;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by taylorstine on 5/29/14.
 */
public class RestAdapterSingleton extends Singleton{
    static RestAdapterSingleton sInstance;
    private RestAdapter mRestAdapter;

    public RestAdapterSingleton(){
        register(RestAdapterSingleton.class, this);
    }

    public synchronized RestAdapter getRestAdapter(Context context){
        if (mRestAdapter == null){
            mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(context.getString(R.string.base_api_url))
                .setConverter(new GsonConverter(((GsonHandler)Singleton.getInstance(GsonHandler.class)).getGson()))
                .build();
        }
        return mRestAdapter;
    }
}
