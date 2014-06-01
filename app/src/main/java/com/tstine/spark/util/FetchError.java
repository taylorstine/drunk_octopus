package com.tstine.spark.util;

import android.text.TextUtils;

import retrofit.RetrofitError;

/**
 * Adapts the retrofit error to the fetchProducts error interface
 */
public class FetchError {

    RetrofitError mError;
    public FetchError(RetrofitError error) {
        mError = error;
    }

    public String getReason() {
        if (mError != null &&
            mError.getResponse() != null &&
            !TextUtils.isEmpty(mError.getResponse().getReason())){
            return mError.getResponse().getReason();
        }
        return "Reason unknown";
    }
}

