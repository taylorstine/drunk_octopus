package com.tstine.spark.fragment;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tstine.spark.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by taylorstine on 6/6/14.
 */
@EFragment(R.layout.account_fragment)
public class AccountFragment extends BaseFragment{


    @ViewById  ListView stats_list;

    @AfterViews
    public void afterViews(){
        stats_list.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.search_field_text_view, getResources().getStringArray(R.array.example_stats)));
    }
}
