package com.tstine.spark.mixin;

import android.widget.AbsListView;

/**
 * Created by taylorstine on 6/2/14.
 */
public interface ScrollStateSubject {

    void notify(AbsListView view, int scrollState);

}
