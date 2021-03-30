package com.dev777popov.appmvpcicerone.mvp.presenter

import com.dev777popov.appmvpcicerone.mvp.api.model.GithubUser
import com.dev777popov.appmvpcicerone.mvp.api.model.RepositoriesUser
import com.dev777popov.appmvpcicerone.mvp.model.repo.GithubRepoUserRepo
import com.dev777popov.appmvpcicerone.mvp.model.repo.GithubUsersRepo
import com.dev777popov.appmvpcicerone.mvp.navigation.IScreens
import com.dev777popov.appmvpcicerone.mvp.presenter.list.IRepoUserListPresenter
import com.dev777popov.appmvpcicerone.mvp.presenter.list.IUserListPresenter
import com.dev777popov.appmvpcicerone.mvp.view.RepoUserView
import com.dev777popov.appmvpcicerone.mvp.view.list.IRepoUserItemView
import com.dev777popov.appmvpcicerone.mvp.view.list.IUserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class RepoUserPresenter(
    private val scheduler: Scheduler,
    private val user: GithubUser?,
    private val githubRepoUserRepo: GithubRepoUserRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<RepoUserView>() {

    class RepoUserPresenter : IRepoUserListPresenter {

        val repoUser = mutableListOf<RepositoriesUser>()

        override var itemClickListener: ((IRepoUserItemView) -> Unit)? = null

        override fun bindView(view: IRepoUserItemView) {
            val repoUser = repoUser[view.pos]
            view.setNameRepo(repoUser.name)
        }

        override fun getCount(): Int = repoUser.size
    }

    val repoUserRepoPresenter = RepoUserPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadData()

        repoUserRepoPresenter.itemClickListener = { view ->
            val repo = repoUserRepoPresenter.repoUser[view.pos]
            router.navigateTo(screen = screens.repo(repo))
        }
    }

    private fun loadData() {
        if (user?.reposUrl != null) {
            viewState.showProgress()
            githubRepoUserRepo.getRepositoriesUser(user)
                .observeOn(scheduler)
                .subscribe({ repo ->
                    repoUserRepoPresenter.repoUser.clear()
                    repoUserRepoPresenter.repoUser.addAll(repo)
                    viewState.updateList()
                    viewState.hideProgress()
                }, {
                    println("onError: ${it.message}")
                })
        }
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}