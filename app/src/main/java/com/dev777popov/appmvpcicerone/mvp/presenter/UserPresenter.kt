package com.dev777popov.appmvpcicerone.mvp.presenter

import com.dev777popov.appmvpcicerone.mvp.model.GithubUserRepo
import com.dev777popov.appmvpcicerone.mvp.model.entity.GithubUser
import com.dev777popov.appmvpcicerone.mvp.presenter.list.IUserListPresenter
import com.dev777popov.appmvpcicerone.mvp.view.UsersView
import com.dev777popov.appmvpcicerone.mvp.view.list.IUserItemView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(val githubUserRepo: GithubUserRepo, val router: Router) :
    MvpPresenter<UsersView>() {

    class UserListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount(): Int = users.size
    }

    val userListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        val users = githubUserRepo.getUsers()
        userListPresenter.users.clear()
        userListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}