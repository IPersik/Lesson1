package com.example.lesson1

import com.example.lesson1.imageconberter.ImageConverterFragment
import com.example.lesson1.interfaces.IScreens
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens{
    override fun imageConverter(): Screen =
        FragmentScreen { ImageConverterFragment() }
}