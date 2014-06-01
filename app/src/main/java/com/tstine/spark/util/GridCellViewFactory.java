package com.tstine.spark.util;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tstine.spark.R;
import com.tstine.spark.model.Product;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by taylorstine on 5/30/14.
 */
@EBean
public class GridCellViewFactory {
    @RootContext Context mContext;

    public View makeView(Product product, View convertView, ViewGroup parent){

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_cell, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder)convertView.getTag();
        handleProductImage(holder, product, convertView);
        handleProductTitle(holder, product);
        handleProductLikeCount(holder, product);
        handleProductCommentCount(holder, product);
        handleProductUserName(holder, product);
        handleProductUserImage(holder, product);
        handleProductPrice(holder, product);
        handleProductCategory(holder, product);

        return convertView;
    }

    protected void handleProductImage(final ViewHolder holder, final Product product, final View parent){
        final ImageView imageView = holder.getvProductImage();

        final ColorDrawable placeHolder = new ColorDrawable();
        try {
            placeHolder.setColor(Color.parseColor(product.getImage().getHolding_color()));
        }catch(NullPointerException e){
            placeHolder.setColor(Color.BLUE);
        }

        imageView.setImageDrawable(placeHolder);

        final ViewTreeObserver observer = parent.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (observer != null && observer.isAlive()) {
                    observer.removeOnGlobalLayoutListener(this);
                }
                int parentWidth = parent.getMeasuredWidth();
                int parentHeight = parent.getMeasuredHeight();
                if (imageView != null) {
                    if (product.getImage() != null && product.getImage().getUrl() != null) {
                        Picasso.with(mContext)
                            .load(product.getImage().getUrl())
                            .placeholder(placeHolder)
                            .skipMemoryCache()
                            .error(placeHolder)
                            .resize(parentWidth, parentHeight)
                            .centerInside()
                            .into(imageView);
                    }
                }
            }
        });
        int width, height;
        try {
            width = product.getImage().getSize().getWidth();
            height = product.getImage().getSize().getHeight();
        }catch(NullPointerException e){
            width = (int)(Math.random() * 400 + 150);
            height = (int)(Math.random() * 400 + 150);
        }

        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        if (params == null){
            params = new ViewGroup.LayoutParams(width, height);
        }

        params.width = width;
        params.height = height;

        imageView.setLayoutParams(params);
    }

    protected void handleProductTitle(ViewHolder holder, Product product){
        if (product != null) {
            holder.getvProductTitle().setText(product.getTitle());
        }
    }

    protected void handleProductLikeCount(ViewHolder holder, Product product){
        if (product != null) {
            holder.getvLikeCountText().setText(String.valueOf(product.getLike_count()));
        }
    }

    protected void handleProductCommentCount(ViewHolder holder, Product product){
        if (product != null) {
            holder.getvCommentCountText().setText(String.valueOf(product.getComment_count()));
        }
    }

    protected void handleProductUserName(ViewHolder holder, Product product){
        holder.getvUserNameText().setText("The User");
    }

    protected void handleProductPrice(ViewHolder holder, Product product){
        try {
            holder.getvPriceText().setText(String.valueOf(product.getPrice()));
        }catch(NullPointerException e){}
    }

    protected void handleProductUserImage(ViewHolder holder, Product product){
        holder.getvUserImage().setImageDrawable(new ColorDrawable(Color.GREEN));
    }

    protected void handleProductCategory(ViewHolder holder, Product product){
        if (product != null) {
            holder.getvCategoryText().setText(product.getCategory());
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
        @InjectView(R.id.price_text) TextView vPriceText;

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

        public TextView getvPriceText() {
            return vPriceText;
        }

        public void setvPriceText(TextView vPriceText) {
            this.vPriceText = vPriceText;
        }
    }
}

