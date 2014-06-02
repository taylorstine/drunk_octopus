package com.tstine.spark.mixin;

import com.tstine.spark.fetchstate.FetchState;

/**
 * Created by taylorstine on 5/30/14.
 */
public interface StateMaintainer {

    void setState(FetchState state);
    FetchState getState();
}
