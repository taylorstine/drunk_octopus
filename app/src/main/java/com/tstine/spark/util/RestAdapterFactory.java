package com.tstine.spark.util;

import android.content.Context;

import com.tstine.spark.R;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by taylorstine on 5/29/14.
 */
public class RestAdapterFactory {

    protected Context mContext;
    protected  GsonFactory mGsonFactory;

    public RestAdapterFactory(Context context, GsonFactory gsonFactory) {
        this.mContext = context;
        this.mGsonFactory = gsonFactory;
    }

    public RestAdapter makeRestAdapter(){
        return new RestAdapter.Builder()
                .setEndpoint(mContext.getString(R.string.base_api_url))
                .setConverter(new GsonConverter(mGsonFactory.makeGson()))
                .build();
    }
}
