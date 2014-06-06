package com.tstine.spark.util;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.tstine.spark.fragment.AccountFragment;
import com.tstine.spark.fragment.AccountFragment_;
import com.tstine.spark.fragment.CartFragment;
import com.tstine.spark.fragment.CartFragment_;
import com.tstine.spark.fragment.SearchFragment_;
import com.tstine.spark.fragment.ShopFragment_;

import org.androidannotations.annotations.EBean;


/**
 * Created by taylorstine on 6/6/14.
 */
public class TabAdapter extends FragmentPagerAdapter{

    private static final String[] TITLE = {"Search", "Shop", "Cart", "You"};
    private FragmentManager mFragmentManager;
    private ViewGroup mContainer;

    public TabAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch(position) {
            case 0: return SearchFragment_.builder().build();
            case 1: return ShopFragment_.builder().build();
            case 2: return CartFragment_.builder().build();
            case 3: return AccountFragment_.builder().build();
            default: return ShopFragment_.builder().build();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position];
    }

    @Override
    public int getCount() {
        return TITLE.length;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        this.mContainer = container;
        return super.instantiateItem(container, position);
    }

    public ViewGroup getContainer() {
        return mContainer;
    }
}
