package com.tstine.spark.view_factory;

import android.view.View;
import android.view.ViewGroup;

import com.tstine.spark.model.Product;

import org.androidannotations.annotations.EBean;

/**
 * Created by taylorstine on 6/5/14.
 */
@EBean
public abstract class AbstractViewFactory {

    public abstract View makeProductGridCellView(Product product, View convertView, ViewGroup parent);
    public abstract View makeProductView(Product product, ViewGroup parent);
}
