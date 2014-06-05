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
@EBean(scope = EBean.Scope.Singleton)
public class NullViewFactory extends AbstractViewFactory {

    @RootContext Context mContext;
    @Override
    public View makeProductGridCellView(Product product, View convertView, ViewGroup parent) {
        return makeNullView();
    }

    @Override
    public View makeProductView(Product product, ViewGroup parent) {
        return makeNullView();
    }

    private View makeNullView(){
        View view = new View(mContext);
        view.setBackground(new ColorDrawable(Color.RED));
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50));
        return view;
    }
}
