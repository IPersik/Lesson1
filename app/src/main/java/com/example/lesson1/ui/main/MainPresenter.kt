package com.example.lesson1.ui.main

import com.example.lesson1.interfaces.IScreens
import com.example.lesson1.interfaces.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter


class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}
