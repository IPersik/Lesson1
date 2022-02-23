package com.example.lesson1.interfaces

interface IImageLoader <T> {

    fun loadInfo(url: String, container: T)
}