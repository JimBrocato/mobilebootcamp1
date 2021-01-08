package com.wcg.mobilebootcamp1_jim.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wcg.mobilebootcamp1_jim.services.ApiClient
import com.wcg.mobilebootcamp1_jim.services.StoreWebService
import com.wcg.mobilebootcamp1_jim.viewmodels.Product
import com.wcg.mobilebootcamp1_jim.viewmodels.ProductListViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    //private val storeWebService: StoreWebService
)  {
    val storeWebService = ApiClient.storeWebService


    fun getProducts() : LiveData<List<Product>> {
        val data = MutableLiveData<List<Product>>()

        storeWebService.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return data
    }





}