package com.example.lesson1.ui.base

interface ImageLoader <T> {

    fun loadInto(url: String, container: T)
}