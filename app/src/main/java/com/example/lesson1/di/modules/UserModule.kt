package com.example.lesson1.di.modules

import com.example.lesson1.db.dao.UserDao
import com.example.lesson1.di.scope.UserScope
import com.example.lesson1.domain.users.GithubUsersRepository
import com.example.lesson1.domain.users.IGithubUsersRepository
import com.example.lesson1.network.GithubApiService
import com.example.lesson1.network.NetworkStatus
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    @UserScope
    fun provideUserRepository(
        apiService: GithubApiService,
        userDao: UserDao,
        networkStatus: NetworkStatus
    ): IGithubUsersRepository {
        return GithubUsersRepository(apiService, userDao, networkStatus)
    }
}