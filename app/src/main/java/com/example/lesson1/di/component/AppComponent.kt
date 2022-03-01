package com.example.lesson1.di.component

import com.example.lesson1.di.modules.*
import com.example.lesson1.ui.main.MainActivity
import com.example.lesson1.ui.main.MainPresenter
import com.example.lesson1.ui.repos.ReposPresenter
import com.example.lesson1.ui.repos.ReposPresenterFactory
import com.example.lesson1.ui.users.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ContextModule::class,
        NavigationModule::class,
        DbModule::class,
        NetworkModule::class,
    ]
)
@Singleton
interface AppComponent {

    fun userSubcomponent(): UserSubcomponent

    fun provideMainPresenter(): MainPresenter

    fun inject(mainActivity: MainActivity)

    fun inject(mainPresenter: MainPresenter)
}