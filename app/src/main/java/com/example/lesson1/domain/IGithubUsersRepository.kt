package com.example.lesson1.domain

import com.example.lesson1.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepository {
    fun getUsers(): Single<List<GithubUser>>
}