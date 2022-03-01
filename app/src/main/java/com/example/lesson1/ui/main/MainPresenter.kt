package com.example.lesson1.ui.main

import com.example.lesson1.screens.AppScreens
import com.example.lesson1.screens.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject


class MainPresenter @Inject constructor(
    private val router: Router,
    private val screens: IScreens,) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.usersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}
