package com.pro.githubdemo.repository

import com.pro.githubdemo.api.ReadmeRetrofitInstance
import com.pro.githubdemo.api.RepositoryRetrofitInstance

class GitHubRepository {

    suspend fun getTopRepositories(sort : String, order : String) = RepositoryRetrofitInstance.apiCall.getTopRepositories(sort, order)
    suspend fun getReadme(url : String) = ReadmeRetrofitInstance(url).readmeApiCall.getReadme()

}