package com.pro.githubdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pro.githubdemo.ViewModel.GitHubViewModel
import com.pro.githubdemo.ViewModel.GitHubViewModelFactory
import com.pro.githubdemo.repository.GitHubRepository
import com.pro.githubdemo.util.GitHubResource
import kotlinx.android.synthetic.main.activity_detail_view.*

class DetailView : AppCompatActivity() {
    lateinit var viewModel : GitHubViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)
        val repository = GitHubRepository()
        val viewModelProviderFactory = GitHubViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(GitHubViewModel::class.java)
        var url = intent.getStringExtra("url").toString()
        val descp = intent.getStringExtra("desc").toString()
        val name = intent.getStringExtra("name").toString()
        val starCount = intent.getStringExtra("count").toString()

        topname.text = name
        descrip.text = descp
        starcount.text = starCount
        Log.i("apiurl","$url" + "/")
        viewModel.getReadme(url+"/")
        viewModel.readme.observe(this) { response ->
            when (response) {
                is GitHubResource.Success -> {
                    response.data?.let { resultResponse ->
                        webView.webViewClient = WebViewClient()
                        webView.loadUrl(resultResponse.html_url.toString())
                    }
                }
                is GitHubResource.Loading -> {

                    response.message?.let {
                        Toast.makeText(this, "Something went wrong :(", Toast.LENGTH_SHORT).show()
                    }
                }
                is GitHubResource.Error -> {

                }
            }
        }

    }
}