package com.dev777popov.appmvpcicerone.di.module

import com.dev777popov.appmvpcicerone.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App = app
}