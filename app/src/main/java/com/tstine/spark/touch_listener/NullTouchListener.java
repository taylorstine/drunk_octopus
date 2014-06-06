package com.tstine.spark.touch_listener;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by taylorstine on 6/5/14.
 */
public class NullTouchListener implements View.OnTouchListener{
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
