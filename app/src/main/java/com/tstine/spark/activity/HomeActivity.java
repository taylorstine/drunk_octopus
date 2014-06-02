package com.tstine.spark.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;

import com.tstine.spark.R;
import com.tstine.spark.model.Product;
import com.tstine.spark.util.GridAdapter;
import com.tstine.spark.util.ScrollListenerDelegate;
import com.tstine.spark.util.ViewClickDelegate;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.Trace;
import org.androidannotations.annotations.ViewById;

/**
 * Created by taylorstine on 5/29/14.
 */
@EActivity(R.layout.main_content)
public class HomeActivity extends Activity {

    @ViewById AbsListView grid_view;
    @Bean ViewClickDelegate mClickDelegate;
    @Bean ScrollListenerDelegate mScrollListenerDelegate;
    @Bean GridAdapter mAdapter;

    @Trace
    @AfterViews
    protected void initProductList(){
        grid_view.setAdapter(mAdapter);
        grid_view.setOnScrollListener(mScrollListenerDelegate);
        grid_view.setOnItemClickListener(mClickDelegate);
    }

    @ItemLongClick(R.id.grid_view)
    public void itemLongClicked(Product product){
        mClickDelegate.doItemLongClick(product);
    }

}

