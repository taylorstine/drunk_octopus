package com.tstine.spark.view_factory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tstine.spark.R;
import com.tstine.spark.view_factory.ViewMaker;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

/**
 * Created by taylorstine on 6/5/14.
 */
@EBean
public class ProductViewMaker extends GridCellViewMaker implements ViewMaker {
    @RootContext Context mContext;

    @Override
    public View makeView(Object data, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View productView = inflater.inflate(R.layout.product_detail, parent, true);
        return super.makeView(data, productView, parent);
    }


}
