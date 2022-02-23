package com.example.lesson1.userInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson1.App
import com.example.lesson1.databinding.FragmentUserInfoBinding
import com.example.lesson1.interfaces.BackButtonListener
import com.example.lesson1.interfaces.UserInfoView
import com.example.lesson1.model.RetrofitGitHubUserRepo
import com.example.lesson1.myschedulers.MySchedulersFactory
import com.example.lesson1.retrofit.GitHubApiFactory
import com.example.lesson1.ui.base.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import rcom.example.lesson1.userInfo.ReposRVAdapter

class UserInfoFragment : MvpAppCompatFragment(), UserInfoView, BackButtonListener {

    companion object {
        private const val ARG_USER = "ARG_USER_LOGIN"

        fun newInstance(userLogin: String) =
            UserInfoFragment().apply { arguments = bundleOf(ARG_USER to userLogin) }
    }

    private val userLogin: String? by lazy {
        arguments?.getString(ARG_USER, "stdimensiy")
    }

    private var vb: FragmentUserInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserInfoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    val presenter: UserInfoPresenter by moxyPresenter {
        UserInfoPresenter(
            userLogin,
            RetrofitGitHubUserRepo(GitHubApiFactory.create()),
            App.instance.router
        )
    }

    var adapter: ReposRVAdapter? = null

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun showLogin(text: String) {
        vb?.tvLogin?.text = text
    }

    override fun setImageAvatar(url: String): Unit = with(vb) {
        this?.imageViewUserAvatar?.let { GlideImageLoader().loadInfo(url, it)}
    }

    override fun showTopString(text: String) {
        vb?.textViewTopString?.text = text
    }

    override fun showCenterString(text: String) {
        TODO("Not yet implemented")
    }

    override fun showBottomString(text: String) {
        TODO("Not yet implemented")
    }


    override fun init() {
        vb?.rvUserRepos?.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.reposListPresenter)
        vb?.rvUserRepos?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showErrorBar() {
        this.vb?.imageViewError?.visibility = View.VISIBLE
    }

    override fun hideErrorBar() {
        this.vb?.imageViewError?.visibility = View.GONE
    }
}