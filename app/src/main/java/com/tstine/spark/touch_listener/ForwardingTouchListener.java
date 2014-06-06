package com.tstine.spark.touch_listener;

import android.view.MotionEvent;
import android.view.View;

import butterknife.OnTouch;

/**
 * Created by taylorstine on 6/5/14.
 */
public class ForwardingTouchListener implements View.OnTouchListener {

    View mForwardee;
    public ForwardingTouchListener(View forwardee){
        this.mForwardee = forwardee;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mForwardee != null){
            mForwardee.onTouchEvent(event);
        }
        return false;
    }
}
