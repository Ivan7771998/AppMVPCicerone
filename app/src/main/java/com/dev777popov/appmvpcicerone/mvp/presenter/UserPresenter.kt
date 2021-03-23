package com.dev777popov.appmvpcicerone.mvp.presenter

import com.dev777popov.appmvpcicerone.mvp.model.GithubUserRepo
import com.dev777popov.appmvpcicerone.mvp.model.entity.GithubUser
import com.dev777popov.appmvpcicerone.mvp.navigation.IScreens
import com.dev777popov.appmvpcicerone.mvp.presenter.list.IUserListPresenter
import com.dev777popov.appmvpcicerone.mvp.view.UsersView
import com.dev777popov.appmvpcicerone.mvp.view.list.IUserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(
    private val scheduler: Scheduler,
    private val githubUserRepo: GithubUserRepo,
    private val router: Router,
    private val screens: IScreens
) :
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

        userListPresenter.itemClickListener = { view ->
            val user = userListPresenter.users[view.pos]
            router.navigateTo(screen = screens.user(user))
        }
    }

    private fun loadData() {
        val users = githubUserRepo.create()
        users.subscribeOn(Schedulers.computation())
            .observeOn(scheduler)
            .subscribe({ user ->
                userListPresenter.users.clear()
                userListPresenter.users.addAll(user)
                viewState.updateList()
            }, {
                println("onError: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}