package com.tstine.spark.util;

import android.app.Activity;

import com.tstine.spark.R;

import org.androidannotations.annotations.EBean;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;

/**
 * Created by taylorstine on 6/5/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class ErrorHandler {

    Activity mActivity;
    public void setActivity(Activity activity){
        this.mActivity = activity;
    }

    public void handleFetchProductError(FetchError error){
        if (mActivity != null) {
            Crouton.make(mActivity, mActivity.getLayoutInflater().inflate(R.layout.crouton_error, null)).show();
        }
        if (error != null) {
            Logger.log("Got fetchProducts error: " + error.getReason());
        }
    }

}
