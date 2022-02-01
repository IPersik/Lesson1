package com.example.lesson1

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }
    fun user() : Screen{
        return FragmentScreen{ UserFragment.newInstance() }
    }
}