package com.tstine.spark.fragment;

import android.database.DataSetObserver;
import android.graphics.drawable.AnimationDrawable;
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
import com.tstine.spark.util.ScrollListenerDelegate;
import com.tstine.spark.util.ViewClickDelegate;
import com.tstine.spark.view_factory.AbstractViewFactory;
import com.tstine.spark.view_factory.ConcreteViewFactory;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.Trace;
import org.androidannotations.annotations.ViewById;

/**
 * Created by taylorstine on 6/6/14.
 */
@EFragment(R.layout.products_main)
public class ShopFragment extends BaseFragment {

    @Bean ViewClickDelegate mClickDelegate;
    @Bean ScrollListenerDelegate mScrollListenerDelegate;
    @Bean GridAdapter mAdapter;
    @Bean(ConcreteViewFactory.class) AbstractViewFactory mViewFactory;
    @Bean GsonOverlord mGsonOverlord;
    @Bean ErrorHandler mErrorHandler;

    @FragmentArg  String productData = null;

    @ViewById
    AbsListView grid_view;
    @ViewById
    ViewGroup product_container;
    @ViewById ViewGroup product_container_parent;
    @ViewById ViewGroup product_scroll_view;
    @ViewById ViewGroup container;
    @ViewById
    ImageView loader;


    @AfterInject
    protected void afterInject(){
        mErrorHandler.setActivity(getActivity());
        mClickDelegate.setActivity(getActivity());
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

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.setHasHeader(false);
            mAdapter.notifyDataSetInvalidated();
        }
    }

    void configureGridView(){
        mAdapter.setViewFactory(mViewFactory);
        grid_view.setAdapter(mAdapter);
        mAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                loader.setVisibility(View.GONE);
                super.onChanged();
            }
        });
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

        mViewFactory.getProductViewMaker().registerOnViewSizeChangeObserver(new OnViewSizeReadyObserver() {
            @Override
            public void notifyViewSizeReady(int width, int height) {
                View view = new View(getActivity());
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
