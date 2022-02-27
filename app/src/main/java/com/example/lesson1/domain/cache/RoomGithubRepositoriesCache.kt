package com.example.lesson1.domain.cache

import com.example.lesson1.db.dao.ReposDao
import com.example.lesson1.db.dao.UserDao
import com.example.lesson1.db.enitity.GithubRepoEntity
import com.example.lesson1.domain.model.GithubRepoModel
import com.example.lesson1.domain.model.GithubUserModel
import com.example.lesson1.domain.model.Owner
import com.example.lesson1.network.GithubApiService
import com.example.lesson1.network.NetworkStatus
import io.reactivex.rxjava3.core.Single

class RoomGithubRepositoriesCache : IRepositoriesCache {
    override fun reposCache(
        networkStatus: NetworkStatus,
        githubApiService: GithubApiService,
        reposDao: ReposDao,
        user: GithubUserModel
    ): Single<List<GithubRepoModel>> {
        return (
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    githubApiService.getRepos(user.reposUrl)
                        .flatMap { repos ->
                            reposDao.insert(repos.map { GithubRepoEntity(it.id, it.name, it.owner.ownerId) })
                            Single.just(repos)
                        }
                } else {
                    reposDao.getAll(user.id)
                        .map { list ->
                            list.map { repo -> GithubRepoModel(repo.name, repo.id, Owner(repo.userId)) }
                        }
                }
            })
    }


}