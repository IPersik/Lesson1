package com.example.lesson1.ui.repos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1.databinding.ItemReposBinding
import com.example.lesson1.domain.model.GithubRepoModel

class ReposAdapter(
    private val itemClickListener: (GithubRepoModel) -> Unit
) : ListAdapter<GithubRepoModel, ReposAdapter.RepoViewHolder>(GithubRepoItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(ItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class RepoViewHolder(private val vb: ItemReposBinding) : RecyclerView.ViewHolder(vb.root) {

        fun bind(repo: GithubRepoModel) {
            vb.root.setOnClickListener { itemClickListener(repo) }
            vb.tvRepoName.text = repo.name
        }
    }
}

object GithubRepoItemCallback : DiffUtil.ItemCallback<GithubRepoModel>() {

    override fun areItemsTheSame(oldItem: GithubRepoModel, newItem: GithubRepoModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GithubRepoModel, newItem: GithubRepoModel): Boolean {
        return oldItem == newItem
    }
}