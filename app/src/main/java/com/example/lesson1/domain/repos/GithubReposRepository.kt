package com.example.lesson1.domain.repos

import com.example.lesson1.db.dao.ReposDao
import com.example.lesson1.db.enitity.GithubRepoEntity
import com.example.lesson1.domain.cache.RoomGithubRepositoriesCache
import com.example.lesson1.domain.model.GithubRepoModel
import com.example.lesson1.domain.model.GithubUserModel
import com.example.lesson1.domain.model.Owner
import com.example.lesson1.network.GithubApiService
import com.example.lesson1.network.NetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubReposRepository(
    private val githubApiService: GithubApiService,
    private val reposDao: ReposDao,
    private val networkStatus: NetworkStatus
) : IGithubReposRepository {

    val rc = RoomGithubRepositoriesCache()
    override fun getRepos(user: GithubUserModel): Single<List<GithubRepoModel>> =
        rc.reposCache(networkStatus, githubApiService, reposDao, user)
}