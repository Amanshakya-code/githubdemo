package com.pro.githubdemo.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pro.githubdemo.DetailView
import com.pro.githubdemo.R
import com.pro.githubdemo.model.Item
import kotlinx.android.synthetic.main.singlerepoitem.view.*

class GitHubTopAdapters() : RecyclerView.Adapter<GitHubTopAdapters.GithuvViewHolder>() {

    inner class GithuvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.html_url == newItem.html_url
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
    val diffUtil = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithuvViewHolder {
        return GithuvViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.singlerepoitem,parent,false))
    }

    override fun onBindViewHolder(holder: GithuvViewHolder, position: Int) {

        val currentObject = diffUtil.currentList[position]

        holder.itemView.apply {
            repoName.text = currentObject.name
            Glide.with(holder.itemView)
                .load(currentObject.owner?.avatar_url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(repoImage)

            maincardView.setOnClickListener {
                val intent = Intent(maincardView.context,DetailView::class.java)
                intent.putExtra("url",currentObject.url)
                intent.putExtra("name",currentObject.name)
                intent.putExtra("desc",currentObject.description)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                maincardView.context.startActivity(intent)
            }
        }

    }
    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

}