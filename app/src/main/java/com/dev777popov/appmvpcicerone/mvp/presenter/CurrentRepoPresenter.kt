package com.dev777popov.appmvpcicerone.mvp.presenter

import com.dev777popov.appmvpcicerone.mvp.api.model.RepositoriesUser
import com.dev777popov.appmvpcicerone.mvp.view.CurrentRepoView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class CurrentRepoPresenter(val repo: RepositoriesUser?, private val router: Router) : MvpPresenter<CurrentRepoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(repo)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}