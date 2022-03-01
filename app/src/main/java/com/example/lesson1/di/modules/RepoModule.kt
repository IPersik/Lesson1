package com.example.lesson1.di.modules

import com.example.lesson1.db.dao.ReposDao
import com.example.lesson1.di.scope.RepoScope
import com.example.lesson1.domain.repos.GithubReposRepository
import com.example.lesson1.domain.repos.IGithubReposRepository
import com.example.lesson1.network.GithubApiService
import com.example.lesson1.network.NetworkStatus
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    @RepoScope
    fun provideRepoRepository(
        apiService: GithubApiService,
        reposDao: ReposDao,
        networkStatus: NetworkStatus
    ): IGithubReposRepository {
        return GithubReposRepository(apiService, reposDao, networkStatus)
    }
}