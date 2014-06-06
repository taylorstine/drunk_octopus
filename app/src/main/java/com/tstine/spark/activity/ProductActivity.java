package com.tstine.spark.activity;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.tstine.spark.R;
import com.tstine.spark.mixin.OnViewSizeReadyObserver;
import com.tstine.spark.model.Product;
import com.tstine.spark.rest.GsonOverlord;
import com.tstine.spark.touch_listener.ForwardingTouchListener;
import com.tstine.spark.util.ErrorHandler;
import com.tstine.spark.util.GridAdapter;
import com.tstine.spark.view_factory.AbstractViewFactory;
import com.tstine.spark.view_factory.ConcreteViewFactory;
import com.tstine.spark.util.ScrollListenerDelegate;
import com.tstine.spark.util.ViewClickDelegate;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.Trace;
import org.androidannotations.annotations.ViewById;

/**
 * Created by taylorstine on 5/29/14.
 */
@EActivity(R.layout.products_main)
public class ProductActivity extends FragmentActivity {

    @Bean ViewClickDelegate mClickDelegate;
    @Bean ScrollListenerDelegate mScrollListenerDelegate;
    @Bean GridAdapter mAdapter;
    @Bean(ConcreteViewFactory.class) AbstractViewFactory mViewFactory;
    @Bean GsonOverlord mGsonOverlord;
    @Bean ErrorHandler mErrorHandler;

    @Extra String productData = null;

    @ViewById AbsListView grid_view;
    @ViewById ViewGroup product_container;
    @ViewById ViewGroup product_container_parent;
    @ViewById ViewGroup product_scroll_view;
    @ViewById ViewGroup container;
    @ViewById ImageView loader;

    @AfterInject
    protected void afterInject(){
        mErrorHandler.setActivity(this);
        mClickDelegate.setActivity(this);
    }

    @Trace
    @AfterViews
    protected void initProductList() {
        configureGridView();
        if (productData != null) {
            configureProductView();
        }
        loader.setBackgroundResource(R.drawable.oreo_loader);
        ((AnimationDrawable) loader.getBackground()).start();
        loader.setVisibility(View.GONE);
    }

    void configureGridView(){
        mAdapter.setViewFactory(mViewFactory);
        grid_view.setAdapter(mAdapter);
        grid_view.setOnScrollListener(mScrollListenerDelegate);
        grid_view.setOnTouchListener(new ForwardingTouchListener(product_scroll_view));
        grid_view.setOnItemClickListener(mClickDelegate);
    }

    void configureProductView(){
        product_container.setVisibility(View.VISIBLE);
        final Product product = mGsonOverlord.humblyRequestGson().fromJson(productData, Product.class);
        final View child = mViewFactory.makeProductView(product, product_container);
        if (child.getParent() == null){
            product_container.addView(child);
        }
        final Context context = this;
        mViewFactory.getProductViewMaker().registerOnViewSizeChangeObserver(new OnViewSizeReadyObserver() {
            @Override
            public void notifyViewSizeReady(int width, int height) {
                View view = new View(context);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height*4));
                product_container_parent.addView(view);

                mAdapter.setHasHeader(true);
                mAdapter.setPaddingViewHeight(height);
                mAdapter.notifyDataSetInvalidated();
            }
        });

    }

    @ItemLongClick(R.id.grid_view)
    public void itemLongClicked(Product product){
        mClickDelegate.doItemLongClick(product);
    }

    private float dpFromPx(float px)
    {
        return px / getResources().getDisplayMetrics().density;
    }

    private float pxFromDp(float dp)
    {
        return dp * getResources().getDisplayMetrics().density;
    }
}

