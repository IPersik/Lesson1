package com.example.lesson1

import android.app.Application
import android.util.Log
import com.example.lesson1.network.NetworkStatus
import com.example.lesson1.rxjava.subscribeToMultiThreadingSingle
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App: Application() {

    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    val navigationHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}