package com.example.lesson1.ui.users.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1.databinding.ItemUserBinding
import com.example.lesson1.model.GithubUser

class UsersAdapter(
    private val itemClickListener: (GithubUser) -> Unit,
) : ListAdapter<GithubUser, UsersAdapter.UserViewHolder>(GithubUserItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.showUser(currentList[position])
    }

    inner class UserViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root) {

        fun showUser(githubUserModel: GithubUser) {
            vb.root.setOnClickListener { itemClickListener(githubUserModel) }
            vb.tvLogin.text = githubUserModel.login
        }
    }
}

object GithubUserItemCallback : DiffUtil.ItemCallback<GithubUser>() {

    override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem == newItem
    }
}

