package com.tstine.spark.view_factory;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;

import com.tstine.spark.model.Product;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by taylorstine on 6/5/14.
 */
@EBean
public abstract class AbstractViewFactory {

    @RootContext Context mContext;

    public abstract View makeProductGridCellView(Product product, View convertView, ViewGroup parent);
    public abstract View makeProductView(Product product, ViewGroup parent);

    public ViewMaker getProductViewMaker(){
        return null;
    };
    public void setProductViewMaker(ViewMaker mProductViewMaker) {}

    public ViewMaker getGridCellViewMaker() {
        return null;
    }

    public void setGridCellViewMaker(ViewMaker mGridCellViewMaker) {}

    public View makePaddingView(int heightPadding){
        View view = new View(mContext);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPadding));
        view.setBackground(new ColorDrawable(Color.TRANSPARENT));
        view.setTag("padding view");
        return view;
    }
}
