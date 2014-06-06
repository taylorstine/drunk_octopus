package com.tstine.spark.view_factory;

import android.view.View;
import android.view.ViewGroup;

import com.tstine.spark.model.Product;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by taylorstine on 6/5/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class ConcreteViewFactory extends AbstractViewFactory {

    @Bean(ProductViewMaker.class) ViewMaker mProductViewMaker;
    @Bean(GridCellViewMaker.class) ViewMaker mGridCellViewMaker;

    @Override
    public View makeProductGridCellView(Product product, View convertView, ViewGroup parent) {
        return getGridCellViewMaker().makeView(product, convertView, parent);
    }

    @Override
    public View makeProductView(Product product, ViewGroup parent) {
        return getProductViewMaker().makeView(product, null, parent);
    }

    @Override
    public ViewMaker getProductViewMaker() {
        return mProductViewMaker;
    }

    @Override
    public void setProductViewMaker(ViewMaker mProductViewMaker) {
        this.mProductViewMaker = mProductViewMaker;
    }

    @Override
    public ViewMaker getGridCellViewMaker() {
        return mGridCellViewMaker;
    }

    @Override
    public void setGridCellViewMaker(ViewMaker mGridCellViewMaker) {
        this.mGridCellViewMaker = mGridCellViewMaker;
    }
}
