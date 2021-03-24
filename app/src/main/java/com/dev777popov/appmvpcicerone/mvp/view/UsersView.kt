package com.dev777popov.appmvpcicerone.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UsersView : MvpView {
    fun init()
    fun updateList()
    fun showProgress()
    fun hideProgress()
}