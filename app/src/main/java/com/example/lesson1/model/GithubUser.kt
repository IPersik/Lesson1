package com.example.lesson1.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    @Expose
    val id: Long,

    @Expose
    val login: String,

    @Expose
    val avatarUrl: String? = null,
): Parcelable
