package com.wcg.mobilebootcamp1_jim.services

import com.wcg.mobilebootcamp1_jim.viewmodels.Product
import retrofit2.Call
import retrofit2.http.GET
import com.wcg.mobilebootcamp1_jim.viewmodels.ProductListViewModel
import retrofit2.http.Path

interface StoreWebService {

//    @GET("products")
//    fun getProducts() : Call<ProductListViewModel>

    @GET("products")
    fun getProducts() : Call<List<Product>>

    @GET("products/{id}")
    fun getProduct(@Path("id") id: Int) : Call<Product>
}