package com.example.lesson1.domain

import com.example.lesson1.model.GithubUser
import com.example.lesson1.network.GithubApiService
import io.reactivex.rxjava3.core.Single


class GithubUsersRepo (private val githubApiService: GithubApiService) : IGithubUsersRepository {

    override fun getUsers(): Single<List<GithubUser>> {
        return githubApiService.getUsers()
    }
}




