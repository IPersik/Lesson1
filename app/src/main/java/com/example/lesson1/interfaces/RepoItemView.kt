package com.example.lesson1.interfaces

/**
 * Интерфейс элементов списка репозиториев
 */

interface RepoItemView : IItemView {

    fun setName(text: String)

    fun setDescription(text: String)
}
