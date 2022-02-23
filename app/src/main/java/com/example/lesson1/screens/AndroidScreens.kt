package com.example.lesson1.screens

import com.example.lesson1.imageconberter.ImageConverterFragment
import com.example.lesson1.interfaces.IScreens
import com.example.lesson1.repoinfo.RepoInfoFragment
import com.example.lesson1.ui.users.UsersFragment
import com.example.lesson1.userInfo.UserInfoFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens{

    override fun users(): Screen = FragmentScreen { UsersFragment.newInstance() }

    override fun userInfo(userLogin: String): Screen =
        FragmentScreen { UserInfoFragment.newInstance(userLogin) }

    override fun repoInfo(repositoryUrl: String): Screen =
        FragmentScreen { RepoInfoFragment.newInstance(repositoryUrl) }

    override fun imageConverter(): Screen =
        FragmentScreen { ImageConverterFragment() }
}