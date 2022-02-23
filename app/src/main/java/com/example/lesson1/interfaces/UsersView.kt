package com.example.lesson1.interfaces

import com.example.lesson1.model.GithubUser
import com.example.lesson1.ui.users.adapter.UsersAdapter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {

    fun init()

    fun updateList()
}