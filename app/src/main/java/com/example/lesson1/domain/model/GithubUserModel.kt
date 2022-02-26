package com.example.lesson1.domain.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserModel(
    @Expose
    val id: Long,

    @Expose
    val login: String,

    @Expose
    val avatarUrl: String? = null,

    @Expose
    val reposUrl: String,
) : Parcelable