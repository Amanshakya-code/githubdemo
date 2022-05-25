package com.pro.githubdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pro.githubdemo.Adapters.GitHubTopAdapters
import com.pro.githubdemo.ViewModel.GitHubViewModel
import com.pro.githubdemo.ViewModel.GitHubViewModelFactory
import com.pro.githubdemo.repository.GitHubRepository
import com.pro.githubdemo.util.GitHubResource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: GitHubTopAdapters
    lateinit var viewModel1 : GitHubViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = GitHubRepository()
        val viewModelProviderFactory = GitHubViewModelFactory(application, repository)
        viewModel1 = ViewModelProvider(this, viewModelProviderFactory).get(GitHubViewModel::class.java)
        adapter = GitHubTopAdapters()
        recylerView.layoutManager = LinearLayoutManager(this)
        recylerView.adapter = adapter

        viewModel1.topRepositories.observe(this){ response ->
            when (response) {
                is GitHubResource.Success -> {
                    response.data?.let {
                        mainpb.visibility = View.GONE
                        adapter.diffUtil.submitList(it.items.toList())
                    }
                }
                is GitHubResource.Loading -> {
                    mainpb.visibility = View.VISIBLE
                }
                is GitHubResource.Error -> {
                    Toast.makeText(this, "something went wrong !!", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }
}