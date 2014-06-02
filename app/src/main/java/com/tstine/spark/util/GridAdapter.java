package com.tstine.spark.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tstine.spark.model.Product;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taylorstine on 5/29/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class GridAdapter extends BaseAdapter{

    @Bean GridCellViewFactory mViewFactory;
    protected List<Product> mProducts;

    public GridAdapter(){
            this.mProducts = new ArrayList<Product>();
    }

    public void appendItems(List<Product> products){
        mProducts.addAll(products);
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Product getItem(int position) {
        return mProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (getItem(position).get_id() != null){
            return (getItem(position)).get_id().hashCode();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mViewFactory.makeView(getItem(position), convertView, parent);
    }

    public GridCellViewFactory getViewFactory() {
        return mViewFactory;
    }
}
