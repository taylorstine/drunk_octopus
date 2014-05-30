package com.tstine.spark.util;

import com.google.gson.Gson;

/**
 * Created by taylorstine on 5/29/14.
 */
public class GsonFactory{
    private Gson mGson;
    public GsonFactory(){}

    protected Gson buildGson(){
        return new Gson();
    };

    public Gson makeGson(){
        return buildGson();
    }
}
