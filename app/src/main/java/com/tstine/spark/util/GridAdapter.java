package com.tstine.spark.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tstine.spark.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taylorstine on 5/29/14.
 */
public class GridAdapter extends BaseAdapter{
    protected Context mContext;
    protected ListViewCellFactory mViewFactory;


    protected List<Product> mProducts;
    public GridAdapter(Context context, ListViewCellFactory viewFactory){
        this.mContext = context;
        this.mProducts = new ArrayList<Product>();
        for (int idx = 0; idx < 40; idx++){
            mProducts.add(new Product());
        }
        this.mViewFactory = viewFactory;
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
}
