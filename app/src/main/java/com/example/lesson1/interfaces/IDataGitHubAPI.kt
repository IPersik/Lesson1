package com.example.lesson1.interfaces

import com.example.lesson1.model.GithubUser
import com.example.lesson1.model.GithubUserAdvanced
import com.example.lesson1.model.Repository
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface IDataGitHubAPI {

    @GET("/users")
    fun getUsers(
        @Query("since") since: Int? = null,
        @Query("per_page") perPage: Int? = null
    ): Single<List<GithubUser>>

    @GET("/users/{username}")
    fun getUserByLogin(
        @Path("username") login: String
    ): Single<GithubUserAdvanced>

    @GET("/users/{username}/repos")
    fun getUserRepos(
        @Path("username") login: String,
        @Query("type") type: String?,
        @Query("sort") sort: String?,
        @Query("direction") direction: String?,
        @Query("per_page") perPage: Int?,
        @Query("page") page: Int?
    ): Single<List<Repository>>

    @GET
    fun getRepositoryByUrl(
        @Url url: String
    ): Single<Repository>


}