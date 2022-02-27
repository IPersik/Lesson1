package com.example.lesson1.domain.cache

import com.example.lesson1.db.dao.ReposDao
import com.example.lesson1.domain.model.GithubRepoModel
import com.example.lesson1.domain.model.GithubUserModel
import com.example.lesson1.network.GithubApiService
import com.example.lesson1.network.NetworkStatus
import io.reactivex.rxjava3.core.Single

interface IRepositoriesCache {

    fun reposCache(networkStatus: NetworkStatus, githubApiService: GithubApiService,
                   reposDao: ReposDao, user: GithubUserModel
    ) : Single<List<GithubRepoModel>>

}