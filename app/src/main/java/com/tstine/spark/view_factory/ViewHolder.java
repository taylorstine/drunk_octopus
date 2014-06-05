package com.tstine.spark.view_factory;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tstine.spark.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
* Created by taylorstine on 6/1/14.
*/
public class ViewHolder {
    @InjectView(R.id.product_image) ImageView product_image;
    //@InjectView(R.id.product_imageview_switcher)  ImageSwitcher product_imageview_switcher;
    //@InjectView(R.id.add_to_cart_banner_button) View vAddToCartBannerButton;
    //@InjectView(R.id.like_banner_button) View vLikeBannerButton;
    @InjectView(R.id.product_title) TextView product_title;
    @InjectView(R.id.like_count_text) TextView like_count_text;
    @InjectView(R.id.comment_count_text) TextView comment_count_text;
    @InjectView(R.id.user_container) View user_container;
    @InjectView(R.id.user_image) ImageView user_image;
    @InjectView(R.id.category_text) TextView user_name_text;
    @InjectView(R.id.category_text) TextView category_text;
    @InjectView(R.id.comment_count_container) ViewGroup comment_count_container;
    //@InjectView(R.id.price_text) TextView vPriceText;
    public ViewHolder(View view){
        ButterKnife.inject(this, view);
    }
}
