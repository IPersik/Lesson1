package com.example.lesson1.di.component

import com.example.lesson1.di.modules.UserModule
import com.example.lesson1.di.scope.UserScope
import com.example.lesson1.ui.users.UsersPresenter
import dagger.Subcomponent

@Subcomponent(
    modules = [
        UserModule::class,
    ]
)
@UserScope
interface UserSubcomponent {

    fun repoSubcomponent(): RepoSubcomponent

    fun provideUsersPresenter(): UsersPresenter
}