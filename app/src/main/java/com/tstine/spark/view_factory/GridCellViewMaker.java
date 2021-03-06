package com.tstine.spark.view_factory;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tstine.spark.R;
import com.tstine.spark.mixin.OnViewSizeReadyObserver;
import com.tstine.spark.model.Product;
import com.tstine.spark.util.Logger;
import com.tstine.spark.view_factory.ViewHolder;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Objects;

/**
 * Created by taylorstine on 5/30/14.
 */
@EBean
public class GridCellViewMaker implements ViewMaker{
    @RootContext Context mContext;

    OnViewSizeReadyObserver mObserver;

    @Override
    public void registerOnViewSizeChangeObserver(OnViewSizeReadyObserver observer) {
        mObserver = observer;
    }

    public View makeView(Object item, View convertView, ViewGroup parent){
        Product product;
        try{
            product = (Product)item;
        }catch (ClassCastException e){
            throw new ClassCastException("Grid cell view factory expected a Product object, but instead" +
                "got a " + item.getClass() + " object");
        }

        if (convertView == null || (convertView.getTag() != null &&convertView.getTag().toString().equalsIgnoreCase("padding view"))){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_cell, parent, false);
        }

        if (convertView.getTag() == null){
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

        holder.comment_count_container.setVisibility(View.GONE);

        return convertView;
    }

    public void handleProductImage(final ViewHolder holder, final Product product, final View parent){
        final ImageView imageView = holder.product_image;
        final ColorDrawable placeHolder = new ColorDrawable();
        try {
            placeHolder.setColor(Color.parseColor(product.getImage().getHolding_color()));
        }catch(NullPointerException e){
            placeHolder.setColor(Color.BLUE);
        }

        int width, height;
        try {
            width = product.getImage().getSize().getWidth();
            height = product.getImage().getSize().getHeight();
        }catch(NullPointerException e){
            width = (int)(Math.random() * 200 + 150);
            height = (int)(Math.random() * 200 + 150);
        }

        final float imageAspect = (float)height/(float)width;

        if (parent != null && parent.getViewTreeObserver() != null) {
            parent.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    if (parent.getViewTreeObserver().isAlive()) {
                        parent.getViewTreeObserver().removeOnPreDrawListener(this);
                    }
                    setImageViewSize(imageView, parent, imageAspect);
                    if (mObserver != null) {
                        if (parent.getViewTreeObserver().isAlive()){
                            parent.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                                @Override
                                public boolean onPreDraw() {
                                    if (parent.getViewTreeObserver().isAlive()){
                                        parent.getViewTreeObserver().removeOnPreDrawListener(this);
                                    }
                                    mObserver.notifyViewSizeReady(parent.getWidth(), parent.getHeight());
                                    return false;
                                }
                            });
                        }

                    }
                    return false;
                }
            });
        }

        Picasso.with(mContext)
            .load(product.getImage().getUrl())
            .placeholder(placeHolder)
            .skipMemoryCache()
            .error(new ColorDrawable(Color.RED))
            .resize(width, height)
            .centerInside()
            .into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    /*setImageViewSize(imageView, parent, imageAspect);
                    int radius = mContext.getResources().getDimensionPixelOffset(R.dimen.small_product_rectangle_corner_radius);
                    Bitmap bitmap =((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    Bitmap rounded = ImageUtils.getRoundedCornerBitmap(bitmap, radius);
                    imageView.setImageBitmap(rounded);*/
                }

                @Override
                public void onError() {

                }
            });

    };

    protected void setImageViewSize(View imageView, View parent, float imageAspect){
        int width = parent.getMeasuredWidth();
        int height = (int)(imageAspect * (float)parent.getMeasuredWidth());
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        if (params == null ){
            params = new ViewGroup.LayoutParams(width, height);
        }

        Logger.log("parent width: %d, parent height: %d; image view width %d", parent.getMeasuredWidth(), parent.getMeasuredHeight(), params.width);
        params.height = height;
        params.width = width;
        imageView.setLayoutParams(params);
    }

    protected void handleProductTitle(ViewHolder holder, Product product){
        if (product != null) {
            holder.product_title.setText(product.getTitle());
        }
    }

    protected void handleProductLikeCount(ViewHolder holder, Product product){
        if (product != null) {
            holder.like_count_text.setText(String.valueOf(product.getLike_count()));
        }
    }

    protected void handleProductCommentCount(ViewHolder holder, Product product){
        if (product != null) {
            holder.comment_count_text.setText(String.valueOf(product.getComment_count()));
        }
    }

    protected void handleProductUserName(ViewHolder holder, Product product){
        holder.user_name_text.setText("The User");
    }

    protected void handleProductPrice(ViewHolder holder, Product product){
        /*try {
            holder.getvPriceText().setText(String.valueOf(product.getPrice()));
        }catch(NullPointerException e){}*/
    }

    protected void handleProductUserImage(ViewHolder holder, Product product){
        //holder.user_image.setImageDrawable(new ColorDrawable(Color.GREEN));
    }

    protected void handleProductCategory(ViewHolder holder, Product product){
        if (product != null) {
            holder.category_text.setText(product.getCategory());
        }
    }
}

