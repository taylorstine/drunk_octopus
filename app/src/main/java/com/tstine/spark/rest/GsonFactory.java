package com.tstine.spark.rest;

import com.google.gson.Gson;

import org.androidannotations.annotations.EBean;

/**
 * Created by taylorstine on 5/29/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class GsonFactory{
    private Gson mGson;

    protected Gson buildGson(){
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }

    public Gson getGson(){
        return buildGson();
    }
}
