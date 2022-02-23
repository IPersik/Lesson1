package com.example.lesson1.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Интерфейс экрана "Информация о пользователе"
 */

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoInfoView :  MvpView {

    fun showLogin(text: String)

    fun showNameRepository(text: String)

    fun showDescriptionRepository(text: String)

    fun init()
}