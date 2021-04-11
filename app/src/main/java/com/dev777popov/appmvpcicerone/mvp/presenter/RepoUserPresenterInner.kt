package com.dev777popov.appmvpcicerone.mvp.presenter

import com.dev777popov.appmvpcicerone.mvp.api.model.GithubUser
import com.dev777popov.appmvpcicerone.mvp.api.model.RepositoriesUser
import com.dev777popov.appmvpcicerone.mvp.model.repo.IGithubRepoUserRepo
import com.dev777popov.appmvpcicerone.mvp.navigation.IScreens
import com.dev777popov.appmvpcicerone.mvp.presenter.list.IRepoUserListPresenter
import com.dev777popov.appmvpcicerone.mvp.view.RepoUserView
import com.dev777popov.appmvpcicerone.mvp.view.list.IRepoUserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Named

class RepoUserPresenter(
    private val user: GithubUser?,
) : MvpPresenter<RepoUserView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var githubRepoUserRepo: IGithubRepoUserRepo

    @field: Named("main")
    @Inject
    lateinit var schedulerMain: Scheduler

    @field: Named("io")
    @Inject
    lateinit var schedulerIo: Scheduler

    class RepoUserPresenterInner : IRepoUserListPresenter {

        val repoUser = mutableListOf<RepositoriesUser>()

        override var itemClickListener: ((IRepoUserItemView) -> Unit)? = null

        override fun bindView(view: IRepoUserItemView) {
            val repoUser = repoUser[view.pos]
            view.setNameRepo(repoUser.name)
        }

        override fun getCount(): Int = repoUser.size
    }

    val repoUserRepoPresenter = RepoUserPresenterInner()

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
            githubRepoUserRepo.getRepositoriesUser(schedulerIo, user)
                .observeOn(schedulerMain)
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