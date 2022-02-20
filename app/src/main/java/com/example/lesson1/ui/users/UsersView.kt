package com.example.lesson1.ui.users

import com.example.lesson1.model.GithubUser
import com.example.lesson1.ui.users.adapter.UsersAdapter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface UsersView : MvpView {

    @AddToEndSingle
    fun updateList(users: List<GithubUser>) {
    }

}