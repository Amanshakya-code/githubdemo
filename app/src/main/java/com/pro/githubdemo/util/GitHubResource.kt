package com.pro.githubdemo.util

sealed class GitHubResource<T> (
    val data : T? = null,
    val message : String? = null
) {
    class Success<T>(data: T?) : GitHubResource<T>(data)
    class Error<T>(message: String?, data: T? = null) : GitHubResource<T>(data, message)
    class Loading<T> : GitHubResource<T>()
}