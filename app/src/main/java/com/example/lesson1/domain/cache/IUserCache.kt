package com.example.lesson1.domain.cache

import com.example.lesson1.db.dao.UserDao
import com.example.lesson1.domain.model.GithubUserModel
import com.example.lesson1.network.GithubApiService
import com.example.lesson1.network.NetworkStatus
import io.reactivex.rxjava3.core.Single

interface IUserCache {

    fun usersCache(networkStatus: NetworkStatus, githubApiService: GithubApiService,
                   userDao: UserDao
    ) : Single<List<GithubUserModel>>
}