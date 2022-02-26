package com.example.lesson1.ui.repos

import com.example.lesson1.domain.model.GithubRepoModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ReposView : MvpView {

    @AddToEndSingle
    fun showRepos(repos: List<GithubRepoModel>?)
}