package com.example.lesson1.screens

import com.example.lesson1.domain.model.GithubUserModel
import com.example.lesson1.ui.repos.ReposFragment
import com.example.lesson1.ui.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface IScreens {

    fun usersScreen(): FragmentScreen
    fun reposScreen(user: GithubUserModel): FragmentScreen
}


object AppScreens : IScreens{

    override fun usersScreen() = FragmentScreen {
        UsersFragment.newInstance()
    }

    override fun reposScreen(user: GithubUserModel) = FragmentScreen {
        ReposFragment.newInstance(user)
    }
}