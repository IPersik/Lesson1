package com.example.lesson1.di.modules

import android.content.Context
import com.example.lesson1.db.GithubDatabase
import com.example.lesson1.db.dao.ReposDao
import com.example.lesson1.db.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun db(context: Context): GithubDatabase {
        return GithubDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun usersDao(db: GithubDatabase): UserDao {
        return db.userDao
    }

    @Provides
    @Singleton
    fun reposDao(db: GithubDatabase): ReposDao {
        return db.reposDao
    }
}