package com.example.lesson1.domain.cache

import com.example.lesson1.db.dao.UserDao
import com.example.lesson1.db.enitity.GithubUserEntity
import com.example.lesson1.domain.model.GithubUserModel
import com.example.lesson1.network.GithubApiService
import com.example.lesson1.network.NetworkStatus
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single

class RoomGithubUsersCache  : IUserCache{

    override fun usersCache(
        networkStatus: NetworkStatus,
        githubApiService: GithubApiService,
        userDao: UserDao
    ) : Single<List<GithubUserModel>> {
        return (
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    githubApiService.getUsers()
                        .flatMap { users ->
                            userDao.insert(
                                users.map {
                                    GithubUserEntity(
                                        it.id,
                                        it.login,
                                        it.avatarUrl ?: "",
                                        it.reposUrl
                                    )
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
            })
    }
}