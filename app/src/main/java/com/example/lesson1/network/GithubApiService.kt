package com.example.lesson1.network

import com.example.lesson1.model.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GithubApiService {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>
}