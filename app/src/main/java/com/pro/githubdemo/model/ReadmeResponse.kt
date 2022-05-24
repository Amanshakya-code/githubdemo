package com.pro.githubdemo.model

data class ReadmeResponse(
    val _links: Links?,
    val content: String?,
    val download_url: String?,
    val encoding: String?,
    val git_url: String?,
    val html_url: String?,
    val name: String?,
    val path: String?,
    val sha: String?,
    val size: Int?,
    val type: String?,
    val url: String?
)