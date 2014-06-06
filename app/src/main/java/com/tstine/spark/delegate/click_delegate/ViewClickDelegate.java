package com.tstine.spark.delegate.click_delegate;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.tstine.spark.R;
import com.tstine.spark.activity.ProductActivity_;
import com.tstine.spark.model.Product;
import com.tstine.spark.rest.GsonOverlord;
import com.tstine.spark.util.ViewAnimator;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Handles the click events for the app.
 */
@EBean
public class ViewClickDelegate implements AdapterView.OnItemClickListener{
    @RootContext FragmentActivity mActivity;
    @Bean GsonOverlord mGsonOverlord;

    /**
     * Handles the animation of the views
     */
    @Bean ViewAnimator mAnimator;
    private Handler mHandler;
    private boolean mNoClickState;

    public ViewClickDelegate(){
        mHandler = new Handler();
        mNoClickState = true;
    }

    public void doItemClick(Product product){

    }

    public void doItemLongClick(Product product){

    }

    /**
     * Occurs when a product in the product grid view is clicked. Launches a new page to show  the
     * information for that product
     * @param product
     * @param parent
     * @param viewClicked
     * @param position
     * @param id
     */
    public void doProductInformationClick(Product product, AdapterView<?> parent, View viewClicked, int position, long id){
        String productData = mGsonOverlord.humblyRequestGson().toJson(product, Product.class);
        ProductActivity_.intent(mActivity).productData(productData).start();
    }

    /**
     * Performs the actions for a like click on a product.  Delegates the animation to the
     * {@link com.tstine.spark.util.ViewAnimator object passed in}
     * @param view the view that was click
     * @param product the data for the view
     */
    public void doProductLikeClick(final View view, final Product product){
        mAnimator.doSpringAnimation(view);
        mAnimator.doHeartAnimation(view);
        doIncrementLikeCount(view, product);
   }

    /**
     * Increments the like count for a particular product
     * @param view
     * @param product
     */
    private void doIncrementLikeCount(View view, Product product){
        product.setLike_count(product.getLike_count() + 1);
        TextView likeCountView = (TextView)view.findViewById(R.id.like_count_text);
        if (likeCountView != null){
            likeCountView.setText(String.valueOf(product.getLike_count()));
        }
    }


    /**
     * Called when an item in the product grid view is clicked.  Times the click so that in the
     * event of a double click, the product is liked, but in the event of a single click, detailed
     * information is shown about the product.
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
        final Product product = ((Product) parent.getAdapter().getItem(position));
        if (mHandler == null){
            mHandler = new Handler();
        }
        if (mNoClickState) {
            mNoClickState = false;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doProductInformationClick(product, parent, view, position, id);
                    mNoClickState = true;
                }
            }, mActivity.getResources().getInteger(R.integer.product_information_click_timeout));

        }else{
            mHandler.removeCallbacksAndMessages(null);
            doProductLikeClick(view, product);
            mNoClickState = true;
        }

    }

    /**
     * Sets a reference to the activity this class is used in.
     * @param mActivity
     */
    public void setActivity(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }
}
