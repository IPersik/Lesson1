package com.example.lesson1.interfaces

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userInfo(userLogin: String): Screen
    fun repoInfo(repositoryName: String): Screen
    fun imageConverter(): Screen

}