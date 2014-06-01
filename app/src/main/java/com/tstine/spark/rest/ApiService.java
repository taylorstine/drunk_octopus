package com.tstine.spark.rest;

import com.tstine.spark.mixin.IProductApiService;
import com.tstine.spark.rest.RestAdapterFactory;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by taylorstine on 5/29/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class ApiService{

    @Bean
    RestAdapterFactory mRestAdapterFactory;

    public IProductApiService getProductApiService(){
         return mRestAdapterFactory
             .makeRestAdapter()
             .create(IProductApiService.class);
    }
}
