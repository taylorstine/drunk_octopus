package com.tstine.spark.mixin;

import com.tstine.spark.model.Product;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by taylorstine on 5/29/14.
 */
public interface IProductApiService {
    @GET("/")
    void getProducts(@Query("limit") int limit, @Query("offset") int offset, Callback<List<Product>> cb);

}
