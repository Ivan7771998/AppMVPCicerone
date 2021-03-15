package com.dev777popov.appmvpcicerone.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface CurrentUserView: MvpView {
    fun setLoginText(txtLogin: String)
}