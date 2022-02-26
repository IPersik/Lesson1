package com.example.lesson1.domain.users

import com.example.lesson1.db.dao.UserDao
import com.example.lesson1.db.enitity.GithubUserEntity
import com.example.lesson1.domain.model.GithubUserModel
import com.example.lesson1.network.GithubApiService
import com.example.lesson1.network.NetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubUsersRepository(
    private val githubApiService: GithubApiService,
    private val userDao: UserDao,
    private val networkStatus: NetworkStatus,
) : IGithubUsersRepository {

    override fun getUsers(): Single<List<GithubUserModel>> = networkStatus.isOnlineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                githubApiService.getUsers()
                    .flatMap { users ->
                        userDao.insert(
                            users.map {
                                GithubUserEntity(it.id, it.login, it.avatarUrl ?: "", it.reposUrl)
                            }
                        )
                        Single.just(users)
                    }
            } else {
                userDao.getAll()
                    .map { users ->
                        users.map { user ->
                            GithubUserModel(user.id, user.login, user.avatarUrl, user.reposUrl)
                        }
                    }
            }
        }
}