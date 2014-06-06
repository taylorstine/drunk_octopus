package com.tstine.spark.view_factory;

import android.view.View;
import android.view.ViewGroup;

import com.tstine.spark.mixin.OnViewSizeReadyObserver;

/**
 * Created by taylorstine on 6/5/14.
 */
public interface ViewMaker {

    public View makeView(Object data, View convertView, ViewGroup parent);
    public void registerOnViewSizeChangeObserver(OnViewSizeReadyObserver observer);
}
