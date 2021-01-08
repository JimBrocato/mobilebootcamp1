package com.wcg.mobilebootcamp1_jim.services

import com.wcg.mobilebootcamp1_jim.BuildConfig
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


object ApiClient {

    const val MainServer = "https://fakestoreapi.com/"

    val retrofitClient: Retrofit.Builder by lazy {

        val levelType: Level
        if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            levelType = Level.BODY else levelType = Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(logging)

        Retrofit.Builder()
                .baseUrl(MainServer)
                .client(okhttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
    }

    val storeWebService: StoreWebService by lazy {
        retrofitClient
            .build()
            .create(StoreWebService::class.java)
    }
}