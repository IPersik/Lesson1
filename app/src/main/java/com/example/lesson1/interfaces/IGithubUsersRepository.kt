package com.example.lesson1.interfaces

import com.example.lesson1.model.GithubUser
import com.example.lesson1.model.GithubUserAdvanced
import com.example.lesson1.model.Repository
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepository {
    fun getUsers(): Single<List<GithubUser>>

    fun getUserByLogin(login: String): Single<GithubUserAdvanced>

    fun getUserRepos(
        login: String,
        type: String?,
        sort: String?,
        direction: String?,
        perPage: Int?,
        page: Int?
    ): Single<List<Repository>>

    fun getRepositoryByUrl(url: String): Single<Repository>
}