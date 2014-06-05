package com.tstine.spark.rest;

import android.content.Context;

import com.tstine.spark.R;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by taylorstine on 5/29/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class RestAdapterFactory {

    @RootContext Context mContext;
    @Bean
    GsonOverlord mGsonFactory;

    public RestAdapter makeRestAdapter(){
        return new RestAdapter.Builder()
                .setEndpoint(mContext.getString(R.string.base_api_url))
                .setConverter(new GsonConverter(mGsonFactory.humblyRequestGson()))
                .build();
    }
}
