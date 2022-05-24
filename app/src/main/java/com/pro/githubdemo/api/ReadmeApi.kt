package com.pro.githubdemo.api

import com.pro.githubdemo.model.ReadmeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ReadmeApi {

    @GET("readme")
    suspend fun getReadme() : Response<ReadmeResponse>

}