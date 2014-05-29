package com.tstine.spark.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.google.gson.Gson;
import com.tstine.spark.R;
import com.tstine.spark.util.AdapterFactory;
import com.tstine.spark.util.Singleton;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by taylorstine on 5/29/14.
 */
public class HomeActivity extends BaseActivity {

    @InjectView(R.id.grid_view)
    AbsListView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content);
        ButterKnife.inject(this);
        mGridView.setAdapter(((AdapterFactory)Singleton.getInstance(AdapterFactory.class)).makeAdapter());
    }


}

