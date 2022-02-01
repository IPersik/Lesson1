package com.example.lesson1

import com.github.terrakok.cicerone.Router
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView{
    fun init()
    fun updateList()
}
