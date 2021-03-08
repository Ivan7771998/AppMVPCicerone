package com.dev777popov.appmvpcicerone.mvp.presenter

import com.dev777popov.appmvpcicerone.mvp.navigation.IScreens
import com.dev777popov.appmvpcicerone.mvp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screen: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen = screen.users())
    }

    fun backClicked() {
        router.exit()
    }
}