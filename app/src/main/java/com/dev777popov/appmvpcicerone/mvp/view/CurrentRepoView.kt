package com.dev777popov.appmvpcicerone.mvp.view

import com.dev777popov.appmvpcicerone.mvp.api.model.RepositoriesUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface CurrentRepoView : MvpView {
    fun init(repo: RepositoriesUser?)
}