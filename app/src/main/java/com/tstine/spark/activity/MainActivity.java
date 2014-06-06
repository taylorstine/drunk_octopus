package com.tstine.spark.activity;

import android.graphics.Color;
import android.support.v4.view.ViewPager;

import com.andraskindler.parallaxviewpager.ParallaxViewPager;
import com.astuetz.PagerSlidingTabStrip;
import com.tstine.spark.R;
import com.tstine.spark.util.TabAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

/**
 * Created by taylorstine on 6/6/14.
 */
@EActivity(R.layout.pager_layout)
public class MainActivity extends BaseActivity {

    @ViewById PagerSlidingTabStrip tabs;
    @ViewById ParallaxViewPager pager;

    @AfterViews
    public void afterViewInject(){

        pager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(1);

        tabs.setViewPager(pager);
        tabs.setIndicatorColorResource(R.color.theme_light);
        tabs.setBackgroundColor(getResources().getColor(R.color.theme));
        tabs.setDividerColor(Color.BLACK);
        tabs.setTextColor(Color.WHITE);
        tabs.setTextSize(getResources().getDimensionPixelSize(R.dimen.tab_text_size));
        tabs.setTabPaddingLeftRight(getResources().getDimensionPixelOffset(R.dimen.tab_padding));

        //TODO add background drawables for each tab
        //tabs.getChildAt(0).setBackgroundResource(R.drawable.shopping_cart);
        //tabs.getChildAt(1).setBackgroundResource();
        //tabs.getChildAt(2).setBackgroundResource();

    }
}
