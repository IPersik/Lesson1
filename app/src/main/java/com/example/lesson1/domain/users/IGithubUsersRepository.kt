package com.example.lesson1.domain.users

import com.example.lesson1.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepository {

    fun getUsers(): Single<List<GithubUserModel>>
}