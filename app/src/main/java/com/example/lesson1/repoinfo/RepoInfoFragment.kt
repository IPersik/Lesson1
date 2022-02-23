package com.example.lesson1.repoinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.lesson1.App
import com.example.lesson1.databinding.FragmentRepositoryInfoBinding
import com.example.lesson1.interfaces.BackButtonListener
import com.example.lesson1.interfaces.RepoInfoView
import com.example.lesson1.model.RetrofitGitHubUserRepo
import com.example.lesson1.retrofit.GitHubApiFactory
import com.example.lesson1.ui.base.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoInfoFragment : MvpAppCompatFragment(), RepoInfoView, BackButtonListener {

    companion object {
        private const val ARG_REPO = "ARG_REPO_URL"

        fun newInstance(repositoryUrl: String) =
            RepoInfoFragment().apply { arguments = bundleOf(ARG_REPO to repositoryUrl) }
    }

    private val repositoryUrl: String? by lazy {
        arguments?.getString(ARG_REPO, "stdimensiy")
    }

    private var vb: FragmentRepositoryInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentRepositoryInfoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    val presenter: RepoInfoPresenter by moxyPresenter {
        RepoInfoPresenter(
            repositoryUrl,
            RetrofitGitHubUserRepo(GitHubApiFactory.create()),
            App.instance.router
        )
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun showLogin(text: String) {
        vb?.tvLogin?.text = text
    }

    override fun showNameRepository(text: String) {
        vb?.tvNameRepository?.text = text
    }

    override fun showDescriptionRepository(text: String) {
        vb?.tvDescription?.text = text
    }

    override fun init() {
        showNameRepository("Загрузка...")
    }
}