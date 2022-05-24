package com.pro.githubdemo.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReadmeRetrofitInstance(url : String) {

    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    private fun getRetrofit(url : String) : Retrofit {
        return Retrofit.Builder().baseUrl(url).client(client).addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    private val retrofit = getRetrofit(url)
    val readmeApiCall = retrofit.create(ReadmeApi::class.java)

}