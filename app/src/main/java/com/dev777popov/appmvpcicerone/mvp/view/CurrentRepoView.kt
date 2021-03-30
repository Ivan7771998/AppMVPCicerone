package com.dev777popov.appmvpcicerone.mvp.view

import com.dev777popov.appmvpcicerone.mvp.api.model.RepositoriesUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface CurrentRepoView : MvpView {
    fun setMainInfo(name: String?, login: String?, avatar_url: String?, description: String?)
    fun setDateInfo(createdAt: String?, updatedAt: String?)
    fun setAdditionalInfo(watchersCount: Int?, forksCount: Int?, language: String?, defaultBranch: String?)
}