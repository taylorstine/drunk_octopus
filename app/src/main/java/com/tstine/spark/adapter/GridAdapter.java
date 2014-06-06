package com.tstine.spark.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ViewSwitcher;

import com.tstine.spark.model.Product;
import com.tstine.spark.util.Logger;
import com.tstine.spark.view_factory.AbstractViewFactory;
import com.tstine.spark.view_factory.ConcreteViewFactory;
import com.tstine.spark.view_factory.GridCellViewMaker;
import com.tstine.spark.view_factory.NullViewFactory;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taylorstine on 5/29/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class GridAdapter extends BaseAdapter{

    @Bean(NullViewFactory.class) AbstractViewFactory  mViewFactory;
    private boolean mHasHeader = false;
    private int mPaddingViewHeight = 0;

    protected List<Product> mProducts;

    public GridAdapter(){
        this.mProducts = new ArrayList<Product>();
    }

    public void appendItems(List<Product> products){
        mProducts.addAll(products);
    }

    @Override
    public int getCount() {
        if (hasHeader()) {
            return mProducts.size() + 2;
        }else{
            return mProducts.size();
        }
    }

    @Override
    public Product getItem(int position) {
        Logger.log("position: " + position);
        if (hasHeader()) {
            if (position >= 2) {
                return mProducts.get(position - 2);
            }else{
                return mProducts.get(position);
            }
        }else{
            return mProducts.get(position);
        }
    }

    public boolean hasHeader(){return mHasHeader;}

    public boolean shouldMakeHeader(int position){
        return hasHeader() && position < 2;
    }

    public void setHasHeader(boolean value){
        this.mHasHeader = value;
    }

    public void setPaddingViewHeight(int height){this.mPaddingViewHeight = height;}
    public int getPaddingViewHeight(){return this.mPaddingViewHeight;}

    public void addHeader(Product product){
        mProducts.add(0, product);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        if (getItem(position) != null && getItem(position).get_id() != null){
            return (getItem(position)).get_id().hashCode();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (shouldMakeHeader(position)){
            return getViewFactory().makePaddingView(getPaddingViewHeight());
        }else{
            return getViewFactory().makeProductGridCellView(getItem(position), convertView, parent);
        }

    }

    public AbstractViewFactory getViewFactory() {
        return mViewFactory;
    }

    public void setViewFactory(AbstractViewFactory mViewFactory) {
        this.mViewFactory = mViewFactory;
    }
}
