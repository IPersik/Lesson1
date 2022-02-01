package com.example.lesson1

interface IListPresenter<V: IItemView> {
    val itemClickListener: ((V) -> Unit)?
    fun bindView(view: UsersRVAdapter.ViewHolder)
    fun getCount(): Int
}