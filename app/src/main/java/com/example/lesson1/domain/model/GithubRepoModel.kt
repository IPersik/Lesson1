package com.example.lesson1.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GithubRepoModel(
    @Expose
    val name: String,
    @Expose
    val id: Long,
    @Expose
    val owner: Owner
)

data class Owner(
    @SerializedName("id")
    @Expose
    val ownerId: Long,
)