package com.example.lesson1.interfaces

interface IListPresenter<V: IItemView> {

    var itemClickListener: ((V) -> Unit)?

    fun getCount(): Int

    fun bindView(view: V)
}