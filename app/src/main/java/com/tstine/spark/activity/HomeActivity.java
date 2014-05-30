package com.tstine.spark.activity;

import android.os.Bundle;
import android.widget.AbsListView;

import com.tstine.spark.R;
import com.tstine.spark.util.ApiService;
import com.tstine.spark.util.GridAdapter;
import com.tstine.spark.util.GsonFactory;
import com.tstine.spark.util.ListViewCellFactory;
import com.tstine.spark.util.ProductDataMediator;
import com.tstine.spark.util.ProductFetcher;
import com.tstine.spark.util.RestAdapterFactory;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by taylorstine on 5/29/14.
 */
public class HomeActivity extends BaseActivity {

    @InjectView(R.id.grid_view)
    AbsListView mGridView;
    ProductDataMediator mDataMediator;
    ProductFetcher mFetcher;
    GsonFactory mGsonFactory;
    RestAdapterFactory mRestAdapterFactory;
    ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content);
        ButterKnife.inject(this);

        mGsonFactory = new GsonFactory();
        mRestAdapterFactory = new RestAdapterFactory(this, mGsonFactory);
        mApiService = new ApiService(this, mRestAdapterFactory);
        mFetcher = new ProductFetcher(this, mApiService.getProductApiService());

        GridAdapter adapter = new GridAdapter(this, new ListViewCellFactory());

        mDataMediator = new ProductDataMediator(this, mGridView, mFetcher, adapter);

        mGridView.setAdapter(adapter);
    }


}

