package com.tstine.spark.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tstine.spark.R;
import com.tstine.spark.model.Product;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by taylorstine on 5/30/14.
 */
public class ListViewCellFactory {
    Context mContext;
    public ListViewCellFactory(Context context){
        this.mContext = context;
    }
    public View makeView(Product product, View convertView, ViewGroup parent){

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_cell, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder)convertView.getTag();
        handleProductImage(holder, product);

        return convertView;
    }

    public void handleProductImage(ViewHolder holder, Product product){
        ImageView view = holder.getvProductImage();
        ViewGroup.LayoutParams params = view.getLayoutParams();
        //TODO: fix this in the api, and fix the color
        params.width = Integer.parseInt(product.getImage().getSize().getWidth());
        params.height = Integer.parseInt(product.getImage().getSize().getHeight());

        ColorDrawable placeHolder =new ColorDrawable(Color.parseColor(product.getImage().getHolding_color()));
        if (view != null){
            Picasso.with(mContext)
                .load(product.getImage().getUrl())
                .placeholder(placeHolder)
                .error(placeHolder)
                .into(view);

        }
    }

    public static class ViewHolder{

        @InjectView(R.id.product_image) ImageView vProductImage;
        @InjectView(R.id.add_to_cart_banner_button) View vAddToCartBannerButton;
        @InjectView(R.id.like_banner_button) View vLikeBannerButton;
        @InjectView(R.id.product_title) TextView vProductTitle;
        @InjectView(R.id.like_count_text) TextView vLikeCountText;
        @InjectView(R.id.comment_count_text)TextView vCommentCountText;
        @InjectView(R.id.user_container) View vUserContainer;
        @InjectView(R.id.user_image) ImageView vUserImage;
        @InjectView(R.id.user_name_text) TextView vUserNameText;
        @InjectView(R.id.category_text) TextView vCategoryText;

        public ViewHolder(View view){
            ButterKnife.inject(this, view);
        }

        public ImageView getvProductImage() {
            return vProductImage;
        }

        public void setvProductImage(ImageView vProductImage) {
            this.vProductImage = vProductImage;
        }

        public View getvAddToCartBannerButton() {
            return vAddToCartBannerButton;
        }

        public void setvAddToCartBannerButton(View vAddToCartBannerButton) {
            this.vAddToCartBannerButton = vAddToCartBannerButton;
        }

        public View getvLikeBannerButton() {
            return vLikeBannerButton;
        }

        public void setvLikeBannerButton(View vLikeBannerButton) {
            this.vLikeBannerButton = vLikeBannerButton;
        }

        public TextView getvProductTitle() {
            return vProductTitle;
        }

        public void setvProductTitle(TextView vProductTitle) {
            this.vProductTitle = vProductTitle;
        }

        public TextView getvLikeCountText() {
            return vLikeCountText;
        }

        public void setvLikeCountText(TextView vLikeCountText) {
            this.vLikeCountText = vLikeCountText;
        }

        public TextView getvCommentCountText() {
            return vCommentCountText;
        }

        public void setvCommentCountText(TextView vCommentCountText) {
            this.vCommentCountText = vCommentCountText;
        }

        public View getvUserContainer() {
            return vUserContainer;
        }

        public void setvUserContainer(View vUserContainer) {
            this.vUserContainer = vUserContainer;
        }

        public ImageView getvUserImage() {
            return vUserImage;
        }

        public void setvUserImage(ImageView vUserImage) {
            this.vUserImage = vUserImage;
        }

        public TextView getvUserNameText() {
            return vUserNameText;
        }

        public void setvUserNameText(TextView vUserNameText) {
            this.vUserNameText = vUserNameText;
        }

        public TextView getvCategoryText() {
            return vCategoryText;
        }

        public void setvCategoryText(TextView vCategoryText) {
            this.vCategoryText = vCategoryText;
        }
    }
}

