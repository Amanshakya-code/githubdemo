package com.pro.githubdemo.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pro.githubdemo.repository.GitHubRepository

class GitHubViewModelFactory(
    val app : Application,
    private val repository: GitHubRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GitHubViewModel(app, repository) as T
    }

}