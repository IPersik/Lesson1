package com.example.lesson1.domain.repos

import com.example.lesson1.domain.model.GithubRepoModel
import com.example.lesson1.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface IGithubReposRepository {

    fun getRepos(user: GithubUserModel): Single<List<GithubRepoModel>>
}