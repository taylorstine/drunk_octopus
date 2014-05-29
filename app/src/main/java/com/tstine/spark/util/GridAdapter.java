package com.tstine.spark.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tstine.spark.mixin.IProductApiService;

/**
 * Created by taylorstine on 5/29/14.
 */
public class GridAdapter extends BaseAdapter{
    protected Context mContext;
    protected IProductApiService mService;
    protected ProductCounterMediator mProductCounter;
    public GridAdapter(Context context, IProductApiService service){
        mService = service;
        mContext = context;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }

    private boolean needsMoreProducts(){

    }

}
