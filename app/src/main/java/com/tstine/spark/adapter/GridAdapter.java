package com.tstine.spark.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tstine.spark.model.Product;
import com.tstine.spark.util.Logger;
import com.tstine.spark.view_factory.AbstractViewFactory;
import com.tstine.spark.view_factory.NullViewFactory;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * The adapter that backs the product grid view.
 * This adapter has extra methods to determine if there should be padding views added to the
 * top of the grid view, and if so the data is provided accordingly
 */
@EBean(scope = EBean.Scope.Singleton)
public class GridAdapter extends BaseAdapter{

    /**
     * The view factory that creates the views for the grid view
     */
    @Bean(NullViewFactory.class) AbstractViewFactory  mViewFactory;

    /**
     * boolean indicating if there are padding views at the top of this list
     */
    private boolean mHasPaddingViewsAtTop = false;

    /**
     * The height of the padding views that go on the top of the grid
     */
    private int mPaddingViewHeight = 0;

    /**
     * The data that is displayed in the adapter, a list of products
     */
    protected List<Product> mProducts;

    public GridAdapter(){
        this.mProducts = new ArrayList<Product>();
    }

    /**
     * Adds products to the data displayed by the grid view.  This does not call
     * {@link #notifyDataSetChanged()} so the client must call it after calling this method
     * @param products the products to append to the list
     */
    public void appendItems(List<Product> products){
        mProducts.addAll(products);
    }

    /**
     * Returns the total number of items in the list.  Adds the number of padding views
     * if the adapter has padding views
     * @return
     */
    @Override
    public int getCount() {
        if (hasPaddingViewsAtTop()) {
            return mProducts.size() + 2;
        }else{
            return mProducts.size();
        }
    }

    /**
     * Returns the product for the given item.  If the adapter had padding views,
     * the index is decremented correctly to give the corresponding product
     * @param position
     * @return
     */
    @Override
    public Product getItem(int position) {
        Logger.log("position: " + position);
        if (hasPaddingViewsAtTop()) {
            if (position >= 2) {
                return mProducts.get(position - 2);
            }else{
                return mProducts.get(position);
            }
        }else{
            return mProducts.get(position);
        }
    }

    public boolean hasPaddingViewsAtTop(){return mHasPaddingViewsAtTop;}

    /**
     * Returns true if the view for the given position should be made as a padding view
     * @param position
     * @return
     */
    public boolean shouldMakePaddingView(int position){
        return hasPaddingViewsAtTop() && position < 2;
    }

    /**
     * Hook to tell the adapter to add padding views to the top of the grid
     * @param value
     */
    public void setHasPaddingViewsAtTop(boolean value){
        this.mHasPaddingViewsAtTop = value;
    }

    public void setPaddingViewHeight(int height){this.mPaddingViewHeight = height;}
    public int getPaddingViewHeight(){return this.mPaddingViewHeight;}


    /**
     * returns a unique identifier for the item, or 0 if no such identifier can be found
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        if (getItem(position) != null && getItem(position).get_id() != null){
            return (getItem(position)).get_id().hashCode();
        }
        return 0;
    }

    /**
     * Delegates the creation of the view to the {@link #mViewFactory} supplied. Calls
     * {@link com.tstine.spark.view_factory.AbstractViewFactory#makePaddingView(int)} if
     * {@link #shouldMakePaddingView(int)} returns true, otherwise returns a call to
     * {@link com.tstine.spark.view_factory.AbstractViewFactory#makeProductGridCellView(com.tstine.spark.model.Product, android.view.View, android.view.ViewGroup)}
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (shouldMakePaddingView(position)){
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
