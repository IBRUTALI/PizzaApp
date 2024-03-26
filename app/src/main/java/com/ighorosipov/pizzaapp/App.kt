package com.ighorosipov.pizzaapp

import android.app.Application
import com.ighorosipov.pizzaapp.di.AppComponent
import com.ighorosipov.pizzaapp.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}