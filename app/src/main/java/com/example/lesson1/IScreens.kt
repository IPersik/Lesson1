package com.example.lesson1

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun settings() = Unit

}