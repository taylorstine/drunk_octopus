package com.tstine.spark.util;

import com.google.gson.Gson;

/**
 * Created by taylorstine on 5/29/14.
 */
public class GsonHandler extends Singleton{
    static GsonHandler sInstance;
    private Gson mGson;
    protected GsonHandler() {
        Singleton.register(GsonHandler.class, this);
    }

    protected Gson buildGson(){
        return new Gson();
    };

    public Gson getGson(){
        if (mGson == null){
            mGson = buildGson();
        }
        return mGson;
    }
}
