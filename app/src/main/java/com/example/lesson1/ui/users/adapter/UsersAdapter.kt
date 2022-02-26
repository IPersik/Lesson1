package com.example.lesson1.ui.users.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1.databinding.ItemUserBinding
import com.example.lesson1.domain.model.GithubUserModel
import com.example.lesson1.ui.base.ImageLoader

class UsersAdapter(
    private val imageLoader: ImageLoader<ImageView>,
    private val itemClickListener: (GithubUserModel) -> Unit,
    ) : ListAdapter<GithubUserModel, UsersAdapter.UserViewHolder>(GithubUserItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.showUser(currentList[position])
    }

    inner class UserViewHolder(val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root){
        fun showUser(githubUserModel: GithubUserModel) {
            vb.root.setOnClickListener { itemClickListener(githubUserModel) }
            vb.tvLogin.text = githubUserModel.login

            if (githubUserModel.avatarUrl != null) {
                imageLoader.loadInfo(githubUserModel.avatarUrl, vb.avatarImageView)
            }
        }
    }
}

object GithubUserItemCallback : DiffUtil.ItemCallback<GithubUserModel>() {

    override fun areItemsTheSame(oldItem: GithubUserModel, newItem: GithubUserModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GithubUserModel, newItem: GithubUserModel): Boolean {
        return oldItem == newItem
    }
}

