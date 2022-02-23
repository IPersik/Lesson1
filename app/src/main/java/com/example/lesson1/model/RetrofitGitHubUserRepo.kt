package com.example.lesson1.model

import com.example.lesson1.interfaces.IDataGitHubAPI
import com.example.lesson1.interfaces.IGithubUsersRepository
import com.example.lesson1.myschedulers.MySchedulersFactory
import io.reactivex.rxjava3.core.Single

class RetrofitGitHubUserRepo(val api:  IDataGitHubAPI) : IGithubUsersRepository {
    override fun getUsers(): Single<List<GithubUser>> {
        return api.getUsers().subscribeOn(MySchedulersFactory.create().io())
    }

    override fun getUserByLogin(login: String): Single<GithubUserAdvanced> {
        return api.getUserByLogin(login).subscribeOn(MySchedulersFactory.create().io())
    }

    override fun getUserRepos(
        login: String,
        type: String?,
        sort: String?,
        direction: String?,
        perPage: Int?,
        page: Int?
    ): Single<List<Repository>> {
        return api.getUserRepos(login, type, sort, direction, perPage, page)
            .subscribeOn(MySchedulersFactory.create().io())
    }

    override fun getRepositoryByUrl(url: String): Single<Repository> {
        return api.getRepositoryByUrl(url).subscribeOn(MySchedulersFactory.create().io())
    }
}