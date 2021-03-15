package com.dev777popov.appmvpcicerone.mvp.presenter

import com.dev777popov.appmvpcicerone.mvp.view.CurrentUserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class CurrentUserPresenter(private val txtLogin: String, private val router: Router) :
    MvpPresenter<CurrentUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLoginText(txtLogin)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}