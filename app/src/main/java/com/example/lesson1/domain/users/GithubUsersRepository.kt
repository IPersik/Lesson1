package com.example.lesson1.domain.users

import com.example.lesson1.db.dao.UserDao
import com.example.lesson1.db.enitity.GithubUserEntity
import com.example.lesson1.domain.cache.IUserCache
import com.example.lesson1.domain.cache.RoomGithubUsersCache
import com.example.lesson1.domain.model.GithubUserModel
import com.example.lesson1.network.GithubApiService
import com.example.lesson1.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GithubUsersRepository  @Inject constructor(
    private val githubApiService : GithubApiService,
    private val userDao : UserDao,
    private val networkStatus: NetworkStatus
    ): IGithubUsersRepository {

    val uc = RoomGithubUsersCache()

    override fun getUsers(): Single<List<GithubUserModel>> = uc.usersCache(networkStatus, githubApiService, userDao)

}