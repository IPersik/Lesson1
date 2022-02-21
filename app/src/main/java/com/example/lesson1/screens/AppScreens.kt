package com.example.lesson1.screens

import com.example.lesson1.imageconberter.ImageConverterFragment
import com.example.lesson1.interfaces.IScreens
import com.example.lesson1.ui.users.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen


object AppScreens {
     fun usersScreen() = FragmentScreen {
        UsersFragment()

    }
     fun imageConverter(): Screen =
        FragmentScreen { ImageConverterFragment() }
}