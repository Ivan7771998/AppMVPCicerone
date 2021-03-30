package com.dev777popov.appmvpcicerone.mvp.view.list

interface IUserItemView : IItemView {
    fun setLogin(login: String?)
    fun loadAvatar(url: String?)
}