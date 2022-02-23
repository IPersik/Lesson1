package com.example.lesson1.model

import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import kotlin.time.DurationUnit

class GithubUsersRepo {
    private val userPlug = GithubUserAdvanced(
        "mojombo",
        1,
        "MDQ6VXNlcjE=")

    private val repositories = listOf(
        GithubUser(
            "mojombo",
            1,
            "MDQ6VXNlcjE="),
        GithubUser(
            "defunkt",
            2,
            "MDQ6VXNlcjI="),
        GithubUser(
            "pjhyett",
            3,
            "MDQ6VXNlcjM="),
        GithubUser(
            "wycats",
            4,
            "MDQ6VXNlcjQ="
        ),
        GithubUser(
            "ezmobius",
            5,
            "MDQ6VXNlcjU="
        ),
        GithubUser(
            "ivey",
            6,
            "MDQ6VXNlcjY="
        ),
        GithubUser(
            "evanphx",
            7,
            "MDQ6VXNlcjc="
        ),
        GithubUser(
            "vanpelt",
            17,
            "MDQ6VXNlcjE3"
        ),
        GithubUser(
            "wayneeseguin",
            18,
            "MDQ6VXNlcjE4"
        ),
        GithubUser(
            "brynary",
            19,
            "MDQ6VXNlcjE5"
        ),
        GithubUser(
            "kevinclark",
            20,
            "MDQ6VXNlcjIw"
        ),
        GithubUser(
            "technoweenie",
            21,
            "MDQ6VXNlcjIx"
        ),
        GithubUser(
            "macournoyer",
            22,
            "MDQ6VXNlcjIy"
        ),
    )

    fun getUsers(): Single<List<GithubUser>> {
        return Single.just(repositories).delay(5L, TimeUnit.SECONDS)
    }

    fun getUserById(id: Int): Single<GithubUserAdvanced> {
        // для тестирования выдаю конкретный результат без привязки id (пока)
        val ss: Single<GithubUserAdvanced>
        if (id == 3) {
            ss = Single.error(Throwable("Запланированная ошибка"))
        } else {
            ss = Single.just(userPlug).delay(3L, TimeUnit.SECONDS)
        }
        return ss
    }
}