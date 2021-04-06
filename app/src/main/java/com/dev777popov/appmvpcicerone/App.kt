package com.dev777popov.appmvpcicerone

import android.app.Application
import com.dev777popov.appmvpcicerone.di.AppComponent
import com.dev777popov.appmvpcicerone.di.module.DaggerAppComponent
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.db.Database

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)

        appComponent = DaggerAppComponent.builder().build()
    }
}