package com.tstine.spark.util;

import com.tstine.spark.R;
import com.tstine.spark.model.Product;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by taylorstine on 6/1/14.
 */
@EBean
public class ViewClickDelegate {
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
        if (mNoClickState) {
            mNoClickState = false;
            mHandler.postDelayed(mSingleClickAction, mActivity.getResources().getInteger(R.integer.product_information_click_timeout));

        }else{
            mHandler.removeCallbacksAndMessages(null);
            doProductLike();
            mNoClickState = true;
        }

    }

    public void doItemLongClick(Product product){

    }

    public void doProductInformationClick(){
        Crouton.makeText(mActivity, "I'll show you", Style.ALERT).show();
    }

    public void doProductLike(){
        Crouton.makeText(mActivity, "you like it", Style.INFO).show();
    }

}
