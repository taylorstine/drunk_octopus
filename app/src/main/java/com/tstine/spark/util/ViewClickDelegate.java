package com.tstine.spark.util;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.tstine.spark.R;
import com.tstine.spark.model.Product;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by taylorstine on 6/1/14.
 */
@EBean
public class ViewClickDelegate implements AdapterView.OnItemClickListener{
    @RootContext  Activity mActivity;
    private Handler mHandler;
    private Runnable mSingleClickAction;
    private boolean mNoClickState;

    public ViewClickDelegate(){
        mHandler = new Handler();
        mSingleClickAction = new Runnable() {
            @Override
            public void run() {
                doProductInformationClick();
                mNoClickState = true;
            }
        };
        mNoClickState = true;
    }
    public void doItemClick(Product product){

    }

    public void doItemLongClick(Product product){

    }

    public void doProductInformationClick(){
        Crouton.makeText(mActivity, "I'll show you", Style.ALERT).show();
    }

    public void doProductLike(final View view, final Product product){
        doSpringAnimation(view);
        doHeartAnimation(view);

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
                Logger.log("spring value: %s", String.valueOf(spring.getCurrentValue()));
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = ((Product) parent.getAdapter().getItem(position));
        if (mNoClickState) {
            mNoClickState = false;
            mHandler.postDelayed(mSingleClickAction, mActivity.getResources().getInteger(R.integer.product_information_click_timeout));

        }else{
            mHandler.removeCallbacks(mSingleClickAction);
            doProductLike(view, product);
            mNoClickState = true;
        }

    }
}
