package com.example.lesson1.ui.base

interface ImageLoader <T> {

    fun loadInfo(url: String, container: T)
}