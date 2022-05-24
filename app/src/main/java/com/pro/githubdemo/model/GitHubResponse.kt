package com.pro.githubdemo.model

data class GitHubResponse(
    val incomplete_results: Boolean?,
    val items: MutableList<Item>,
    val total_count: Int?
)