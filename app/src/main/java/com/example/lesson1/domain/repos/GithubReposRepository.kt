package com.example.lesson1.domain.repos

import com.example.lesson1.db.dao.ReposDao
import com.example.lesson1.db.enitity.GithubRepoEntity
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

    override fun getRepos(user: GithubUserModel): Single<List<GithubRepoModel>> = networkStatus.isOnlineSingle()
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
        }
}