package com.tstine.spark.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.tstine.spark.R;

import org.androidannotations.annotations.EBean;

/**
 * Created by taylorstine on 6/6/14.
 */
@EBean
public class ViewAnimator {
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

}
