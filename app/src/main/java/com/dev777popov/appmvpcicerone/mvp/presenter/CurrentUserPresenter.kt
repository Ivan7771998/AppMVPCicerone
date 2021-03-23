package com.dev777popov.appmvpcicerone.mvp.presenter

import com.dev777popov.appmvpcicerone.mvp.model.entity.GithubUser
import com.dev777popov.appmvpcicerone.mvp.view.CurrentUserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class CurrentUserPresenter(private val user: GithubUser?, private val router: Router) :
    MvpPresenter<CurrentUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (user != null) {
            viewState.setLoginText(user.login)
        }
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}