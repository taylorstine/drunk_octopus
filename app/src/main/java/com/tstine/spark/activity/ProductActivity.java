package com.tstine.spark.activity;

import android.content.Context;
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
import com.tstine.spark.adapter.GridAdapter;
import com.tstine.spark.view_factory.AbstractViewFactory;
import com.tstine.spark.view_factory.ConcreteViewFactory;
import com.tstine.spark.delegate.scroll_delegate.ScrollListenerDelegate;
import com.tstine.spark.delegate.click_delegate.ViewClickDelegate;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.Trace;
import org.androidannotations.annotations.ViewById;

/**
 * An Activity that shows the information for a product and a list of
 * cross sale products
 */
@EActivity(R.layout.products_main)
public class ProductActivity extends BaseActivity {

    /**
     * The object clicks on the grid view of products
     * are forwarded to.
     * @see {@link #configureGridView()}
     */
    @Bean ViewClickDelegate mClickDelegate;

    /**
     * The object that listens to the scroll actions of the
     * grid view
     * @see {@link #configureGridView()}
     */
    @Bean ScrollListenerDelegate mScrollListenerDelegate;

    /**
     * The adapter that holds the data for the grid view
     * @see {@link #configureGridView()}
     */
    @Bean GridAdapter mAdapter;

    /**
     * The View factory that is used to create the views for the grid view
     * and the product detail view
     */
    @Bean(ConcreteViewFactory.class) AbstractViewFactory mViewFactory;

    /**
     * A reference to the object that manages the Gson deserializer
     */
    @Bean GsonOverlord mGsonOverlord;

    /**
     * A reference to the object that handles errors
     */
    @Bean ErrorHandler mErrorHandler;

    /**
     * Extra passed in to the activity.  This variable contains the
     * Json for a product to display above the grid view
     */
    @Extra String productData = null;

    /**
     * The grid view for the cross sales listed below the product
     */
    @ViewById AbsListView grid_view;

    /**
     * The container for the featured product
     */
    @ViewById ViewGroup product_container;

    /**
     * The parent of the {@link #product_container}
     */
    @ViewById ViewGroup product_container_parent;

    /**
     * The scroll view in which the {@link #product_container_parent} and
     * {@link #product_container} are contained
     */
    @ViewById ViewGroup product_scroll_view;

    /**
     * The root view of the layout
     */
    @ViewById ViewGroup container;

    /**
     * The loader view
     */
    @ViewById ImageView loader;

    /**
     * Sets the activity for the error handler and the click delegate
     */
    @AfterInject
    protected void afterInject(){
        mErrorHandler.setActivity(this);
        mClickDelegate.setActivity(this);
    }

    /**
     * Initializes the product grid, and if necessary the
     * main product view.  The main product view is only shown if
     * the {@link #productData} extra is not null.
     * The loader is then hidden
     * TODO implement the loader visibility/invisiblity methods
     */
    @Trace
    @AfterViews
    protected void afterViewInject() {
        configureGridView();
        if (productData != null) {
            configureProductView();
        }
        loader.setBackgroundResource(R.drawable.oreo_loader);
        ((AnimationDrawable) loader.getBackground()).start();
        loader.setVisibility(View.GONE);
    }

    /**
     * Configures the grid view.
     */
    void configureGridView(){
        mAdapter.setViewFactory(mViewFactory);
        grid_view.setAdapter(mAdapter);
        grid_view.setOnScrollListener(mScrollListenerDelegate);
        grid_view.setOnTouchListener(new ForwardingTouchListener(product_scroll_view));
        grid_view.setOnItemClickListener(mClickDelegate);
    }

    /**
     * Configures the main product view. If this is called, then {@link #productData} is not null.
     * Sets the visibility of the product container to visible.
     * Deserializes the product data json and delegates the view creation to the view factory,
     * supplying the {@link #product_container} as the parent.
     * <p>
     * {@link com.tstine.spark.view_factory.ViewMaker#registerOnViewSizeChangeObserver(com.tstine.spark.mixin.OnViewSizeReadyObserver)}
     * is called to attach a new listener that adds padding views to the grid view, so that the product can be displayed below.
     * When this callback is called the {@link #product_container_parent} size is also expanded to allow for a full scroll of the container
     */
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

                mAdapter.setHasPaddingViewsAtTop(true);
                mAdapter.setPaddingViewHeight(height);
                mAdapter.notifyDataSetInvalidated();
            }
        });

    }

    /**
     * Delegates long clicks to the click delegate
     * @param product
     */
    @ItemLongClick(R.id.grid_view)
    public void itemLongClicked(Product product){
        mClickDelegate.doItemLongClick(product);
    }
}

