package com.dev777popov.appmvpcicerone.mvp.presenter

import com.dev777popov.appmvpcicerone.mvp.model.repo.GithubUsersRepo
import com.dev777popov.appmvpcicerone.mvp.api.model.GithubUser
import com.dev777popov.appmvpcicerone.mvp.model.repo.IGithubUsersRepo
import com.dev777popov.appmvpcicerone.mvp.navigation.IScreens
import com.dev777popov.appmvpcicerone.mvp.presenter.list.IUserListPresenter
import com.dev777popov.appmvpcicerone.mvp.view.UsersView
import com.dev777popov.appmvpcicerone.mvp.view.list.IUserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter(
    private val scheduler: Scheduler
) : MvpPresenter<UsersView>() {

    @Inject lateinit var screens: IScreens
    @Inject lateinit var router: Router
    @Inject lateinit var githubUserRepo: IGithubUsersRepo

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            view.loadAvatar(user.avatarUrl)
        }

        override fun getCount(): Int = users.size
    }

    val userListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadData()

        userListPresenter.itemClickListener = { view ->
            val user = userListPresenter.users[view.pos]
            router.navigateTo(screen = screens.user(user))
        }
    }

    private fun loadData() {
        viewState.showProgress()
        githubUserRepo.getUsers()
            .observeOn(scheduler)
            .subscribe({ user ->
                userListPresenter.users.clear()
                userListPresenter.users.addAll(user)
                viewState.updateList()
                viewState.hideProgress()
            }, {
                println("onError: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}