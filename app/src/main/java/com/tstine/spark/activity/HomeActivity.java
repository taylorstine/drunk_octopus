package com.tstine.spark.activity;

import android.app.Activity;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.RelativeLayout;

import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.tstine.spark.R;
import com.tstine.spark.model.Product;
import com.tstine.spark.rest.GsonOverlord;
import com.tstine.spark.util.GridAdapter;
import com.tstine.spark.util.Logger;
import com.tstine.spark.view_factory.AbstractViewFactory;
import com.tstine.spark.view_factory.ConcreteViewFactory;
import com.tstine.spark.util.ScrollListenerDelegate;
import com.tstine.spark.util.ViewClickDelegate;

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
@EActivity(R.layout.main_content)
public class HomeActivity extends Activity {

    @Bean ViewClickDelegate mClickDelegate;
    @Bean ScrollListenerDelegate mScrollListenerDelegate;
    @Bean GridAdapter mAdapter;
    @Bean(ConcreteViewFactory.class)AbstractViewFactory mViewFactory;
    @Bean GsonOverlord mGsonOverlord;

    @Extra String productData = null;

    @ViewById AbsListView grid_view;
    @ViewById ViewGroup product_container;

    @Trace
    @AfterViews
    protected void initProductList() {
        configureGridView();
        if (productData != null) {
            configureProductView();
        }
    }

    void configureGridView(){
        mAdapter.setViewFactory(mViewFactory);
        grid_view.setAdapter(mAdapter);
        grid_view.setOnScrollListener(mScrollListenerDelegate);
        grid_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                product_container.onTouchEvent(event);
                return false;
            }
        });
        grid_view.setOnItemClickListener(mClickDelegate);
    }

    void configureProductView(){
        product_container.setVisibility(View.VISIBLE);
        final Product product = mGsonOverlord.humblyRequestGson().fromJson(productData, Product.class);
        final View child = mViewFactory.makeProductView(product, product_container);
        if (child.getParent() == null){
            product_container.addView(child);
        }
        product_container.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (product_container != null &&
                    product_container.getViewTreeObserver().isAlive()){
                    product_container.getViewTreeObserver().removeOnPreDrawListener(this);
                }
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(product_container.getLayoutParams());
                params.height*=2;
                product_container.setLayoutParams(params);
                return false;
            }
        });

    }

    @ItemLongClick(R.id.grid_view)
    public void itemLongClicked(Product product){
        mClickDelegate.doItemLongClick(product);
    }

}

