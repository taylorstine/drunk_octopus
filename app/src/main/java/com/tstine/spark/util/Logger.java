package com.tstine.spark.util;

import android.util.Log;

/**
 * Created by taylorstine on 5/31/14.
 */
public class Logger {
    public static void log(String log, Object... strings){
        String logStatement = "";
        if (strings != null){
            logStatement = String.format(log, strings);
        }
        Log.d("SPARK DEBUG", logStatement);
    }
}
