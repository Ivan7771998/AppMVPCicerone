package com.dev777popov.appmvpcicerone.di.module

import com.dev777popov.appmvpcicerone.App
import com.dev777popov.appmvpcicerone.mvp.navigation.IScreens
import com.dev777popov.appmvpcicerone.ui.navigation.AndroidScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class CiceroneModule {

    val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    fun router(): Router = cicerone.router

    @Provides
    fun screens(): IScreens = AndroidScreens()
}