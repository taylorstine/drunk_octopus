package com.tstine.spark.util;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.tstine.spark.R;
import com.tstine.spark.activity.ProductActivity_;
import com.tstine.spark.model.Product;
import com.tstine.spark.rest.GsonOverlord;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by taylorstine on 6/1/14.
 */
@EBean
public class ViewClickDelegate implements AdapterView.OnItemClickListener{
    @RootContext FragmentActivity mActivity;
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
        String productData = mGsonOverlord.humblyRequestGson().toJson(product, Product.class);
        ProductActivity_.intent(mActivity).productData(productData).start();
    }

    public void doProductLike(final View view, final Product product){
        doSpringAnimation(view);
        doHeartAnimation(view);
        doIncrementLikeCount(view, product);
   }

    public void doIncrementLikeCount(View view, Product product){
        product.setLike_count(product.getLike_count() + 1);
        TextView likeCountView = (TextView)view.findViewById(R.id.like_count_text);
        if (likeCountView != null){
            likeCountView.setText(String.valueOf(product.getLike_count()));
        }
    }

    public void doHeartAnimation(final View view){
        final View heart = view.findViewById(R.id.like_notification);
        if (heart != null) {
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
        if (mHandler == null){
            mHandler = new Handler();
        }
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

    public void setActivity(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }
}
