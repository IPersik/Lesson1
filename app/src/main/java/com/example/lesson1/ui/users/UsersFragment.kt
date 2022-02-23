package com.example.lesson1.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson1.App
import com.example.lesson1.databinding.FragmentUserBinding
import com.example.lesson1.interfaces.UsersView
import com.example.lesson1.model.GithubUser
import com.example.lesson1.interfaces.BackButtonListener
import com.example.lesson1.model.RetrofitGitHubUserRepo
import com.example.lesson1.myschedulers.MySchedulersFactory
import com.example.lesson1.retrofit.GitHubApiFactory
import com.example.lesson1.ui.base.GlideImageLoader
import com.example.lesson1.ui.users.adapter.UsersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(
            RetrofitGitHubUserRepo(GitHubApiFactory.create()),
            App.instance.router
        )
    }

    var adapter : UsersAdapter? = null

    private var vb: FragmentUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )=  FragmentUserBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
        vb?.btnGoToImgConverter?.setOnClickListener { presenter.goToImageConverter() }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}
