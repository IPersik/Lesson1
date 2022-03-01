package com.example.lesson1

import android.app.Application
import com.example.lesson1.db.GithubDatabase
import com.example.lesson1.di.component.DaggerAppComponent
import com.example.lesson1.di.component.RepoSubcomponent
import com.example.lesson1.di.component.UserSubcomponent
import com.example.lesson1.di.modules.ContextModule
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    var usersSubcomponent: UserSubcomponent? = null
        private set
    var reposSubcomponent: RepoSubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    fun initUserSubcomponent(): UserSubcomponent {
        usersSubcomponent = appComponent.userSubcomponent()
        return usersSubcomponent!!
    }

    fun initRepoSubcomponent(): RepoSubcomponent {
        reposSubcomponent = appComponent.userSubcomponent().repoSubcomponent()
        return reposSubcomponent!!
    }

    fun destroyRepoScope() {
        reposSubcomponent = null
    }

    fun destroyUserScope() {
        usersSubcomponent = null
    }


    companion object {

        private var _instance: App? = null
        val instance
            get() = _instance!!
    }
}