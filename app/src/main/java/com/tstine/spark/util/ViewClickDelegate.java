package com.tstine.spark.util;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.tstine.spark.R;
import com.tstine.spark.activity.HomeActivity;
import com.tstine.spark.activity.HomeActivity_;
import com.tstine.spark.model.Product;
import com.tstine.spark.rest.GsonOverlord;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.TextView;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by taylorstine on 6/1/14.
 */
@EBean
public class ViewClickDelegate implements AdapterView.OnItemClickListener{
    @RootContext  Activity mActivity;
    @Bean GsonOverlord mGsonOverlord;
    private Handler mHandler;
    private boolean mNoClickState;

    public ViewClickDelegate(){
        mHandler = new Handler();
        mNoClickState = true;
    }
    public void doItemClick(Product product){

    }

    public void doItemLongClick(Product product){

    }

    public void doProductInformationClick(Product product, AdapterView<?> parent, View viewClicked, int position, long id){
        HomeActivity_.intent(mActivity).productData(mGsonOverlord.humblyRequestGson().toJson(product,Product.class)).start();
        //dumpScreenToBitmap(view, parent);
        //zoomInToBitmap();
        //startProductActivity();
    }

    public void dumpScreenToBitmap(View view, View parent){
        Bitmap output = Bitmap.createBitmap(parent.getMeasuredWidth(), parent.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

    }



    public void doProductLike(final View view, final Product product){
        doSpringAnimation(view);
        doHeartAnimation(view);
        doIncrementLikeCount(view, product);
   }

    public void doIncrementLikeCount(View view, Product product){
        product.setLike_count(product.getLike_count() + 1);
        ((TextView)view.findViewById(R.id.like_count_text)).setText(String.valueOf(product.getLike_count()));
    }

    public void doHeartAnimation(final View view){
        final View heart = view.findViewById(R.id.like_notification);

        final ValueAnimator fadeInAnimation = ObjectAnimator.ofFloat(heart, "alpha", 0.0f, 1.0f);
        final ValueAnimator fadeOutAnimation = ObjectAnimator.ofFloat(heart, "alpha", 1.0f, 0.0f);
        fadeInAnimation.setInterpolator(new DecelerateInterpolator());
        fadeInAnimation.setDuration(200);
        fadeOutAnimation.setInterpolator(new DecelerateInterpolator());
        fadeOutAnimation.setDuration(200);
        fadeOutAnimation.setStartDelay(500);

        AnimatorSet set = new AnimatorSet();
        set.play(fadeInAnimation).before(fadeOutAnimation);
        set.start();

    }
    public void doSpringAnimation(final View view){
        SpringSystem springSystem = SpringSystem.create();
        Spring spring = springSystem.createSpring();
        spring.addListener(new SimpleSpringListener(){
            @Override
            public void onSpringUpdate(Spring spring) {
                float high = 1f, low = 0.80f;
                float mid = (high+low) /2.0f;

                float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, high, low);

                if (mappedValue < mid){
                    mappedValue = high - mappedValue + low;
                }
                view.setScaleX(mappedValue);
                view.setScaleY(mappedValue);
            }


        });
        spring.setEndValue(1);

    }

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
        final Product product = ((Product) parent.getAdapter().getItem(position));
        if (mNoClickState) {
            mNoClickState = false;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doProductInformationClick(product, parent, view, position, id);
                    mNoClickState = true;
                }
            }, mActivity.getResources().getInteger(R.integer.product_information_click_timeout));

        }else{
            mHandler.removeCallbacksAndMessages(null);
            doProductLike(view, product);
            mNoClickState = true;
        }

    }
}
