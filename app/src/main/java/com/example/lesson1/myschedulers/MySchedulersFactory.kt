package com.example.lesson1.myschedulers

import com.example.lesson1.interfaces.IMySchedulers


object MySchedulersFactory {
    fun create(): IMySchedulers = MySchedulers()
}