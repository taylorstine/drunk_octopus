package com.tstine.spark.adapter;

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
 * An adapter for the main view pager.  Returns a fragment depending on the position
 * of view pager
 * @see #getItem(int)
 */
public class TabPagerAdapter extends FragmentPagerAdapter{

    private static final String[] TITLE = {"Search", "Shop", "Cart", "You"};
    private FragmentManager mFragmentManager;
    private ViewGroup mContainer;

    public TabPagerAdapter(FragmentManager fm){
        super(fm);
    }

    /**
     * Depending on the position value passed in returns one of the following:
     * <ul>
     *     <li>0: {@link com.tstine.spark.fragment.SearchFragment}</li>
     *     <li>1: {@link com.tstine.spark.fragment.ShopFragment}</li>
     *     <li>2: {@link com.tstine.spark.fragment.CartFragment}</li>
     *     <li>3: {@link com.tstine.spark.fragment.AccountFragment}</li>
     *     <li>default: {@link com.tstine.spark.fragment.ShopFragment}</li>
     * </ul>
     * @param position
     * @return
     */
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


    /**
     * Saves a reference to the container for the fragments, held by {@link #mContainer}
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        this.mContainer = container;
        return super.instantiateItem(container, position);
    }

    public ViewGroup getContainer() {
        return mContainer;
    }
}
