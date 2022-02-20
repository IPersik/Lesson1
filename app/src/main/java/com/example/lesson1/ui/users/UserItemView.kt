package com.example.lesson1.ui.users

import com.example.lesson1.ui.base.IItemView

interface UserItemView: IItemView {
    fun setLogin(login: String)
}