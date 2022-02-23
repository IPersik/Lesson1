package com.example.lesson1.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface UserInfoView :  ErrorView, MvpView {
    fun showLogin(text: String)

    fun setImageAvatar(url: String)

    fun showTopString(text: String)

    fun showCenterString(text: String)

    fun showBottomString(text: String)
    fun init()

    fun updateList()
}