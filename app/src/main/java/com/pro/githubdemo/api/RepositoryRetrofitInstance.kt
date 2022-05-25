package com.pro.githubdemo.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryRetrofitInstance {

    companion object{
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(logging).build()
            Retrofit.Builder().baseUrl("https://api.github.com/search/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val apiCall by lazy {
            retrofit.create(GitHubApi::class.java)
        }
    }
}