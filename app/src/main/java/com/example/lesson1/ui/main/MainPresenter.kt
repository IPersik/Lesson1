package com.example.lesson1.ui.main

import com.example.lesson1.MainView
import com.example.lesson1.screens.AppScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter


class MainPresenter (val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AppScreens.usersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}
