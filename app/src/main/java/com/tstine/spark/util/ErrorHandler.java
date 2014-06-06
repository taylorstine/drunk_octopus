package com.tstine.spark.util;

import android.app.Activity;
import android.os.Handler;
import android.view.View;

import com.tstine.spark.R;
import com.tstine.spark.rest.FetchError;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.LifecycleCallback;

/**
 * Created by taylorstine on 6/5/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class ErrorHandler {

    Activity mActivity;
    private boolean mCanPostError = true;
    private Handler mHandler;
    @AfterInject
    public void afterInject(){
        mHandler = new Handler();
    }
    public void setActivity(Activity activity){
        this.mActivity = activity;
    }

    public void handleFetchProductError(FetchError error){
        if (mActivity != null && mCanPostError) {
            final Crouton crouton = Crouton.make(mActivity, mActivity.getLayoutInflater().inflate(R.layout.crouton_error, null));
            crouton.setLifecycleCallback(new LifecycleCallback() {
                @Override
                public void onDisplayed() {
                    mCanPostError = false;
                }

                @Override
                public void onRemoved() {
                    mCanPostError = false;
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mCanPostError = true;
                        }
                    }, 3000);
                }
            });
            crouton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crouton.hide();
                }
            }).show();

        }
        if (error != null) {
            Logger.log("Got fetchProducts error: " + error.getReason());
        }
    }

}
