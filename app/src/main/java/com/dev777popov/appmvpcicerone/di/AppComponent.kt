package com.dev777popov.appmvpcicerone.di

import com.dev777popov.appmvpcicerone.di.module.*
import com.dev777popov.appmvpcicerone.mvp.model.repo.GithubUsersRepo
import com.dev777popov.appmvpcicerone.mvp.presenter.CurrentRepoPresenter
import com.dev777popov.appmvpcicerone.mvp.presenter.MainPresenter
import com.dev777popov.appmvpcicerone.mvp.presenter.RepoUserPresenter
import com.dev777popov.appmvpcicerone.mvp.presenter.UsersPresenter
import com.dev777popov.appmvpcicerone.ui.activity.MainActivity
import com.dev777popov.appmvpcicerone.ui.adapter.RepoUserRVAdapter
import com.dev777popov.appmvpcicerone.ui.adapter.UserRVAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class,
        RepoModule::class,
        ImageModule::class,
        CacheModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userRVAdapter: UserRVAdapter)
    fun inject(currentRepoPresenter: CurrentRepoPresenter)
    fun inject(repoUserPresenter: RepoUserPresenter)
}