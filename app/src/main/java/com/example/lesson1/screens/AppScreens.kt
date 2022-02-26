package com.example.lesson1.screens

import com.example.lesson1.domain.model.GithubUserModel
import com.example.lesson1.ui.repos.ReposFragment
import com.example.lesson1.ui.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AppScreens {

    fun usersScreen() = FragmentScreen {
        UsersFragment.newInstance()
    }

    fun reposScreen(user: GithubUserModel) = FragmentScreen {
        ReposFragment.newInstance(user)
    }
}