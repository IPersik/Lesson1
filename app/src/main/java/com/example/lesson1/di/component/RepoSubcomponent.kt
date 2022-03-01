package com.example.lesson1.di.component

import com.example.lesson1.di.modules.RepoModule
import com.example.lesson1.di.scope.RepoScope
import com.example.lesson1.ui.repos.ReposPresenterFactory
import dagger.Subcomponent

@Subcomponent(
    modules = [RepoModule::class]
)
@RepoScope
interface RepoSubcomponent {

    fun provideReposPresenterFactory(): ReposPresenterFactory
}