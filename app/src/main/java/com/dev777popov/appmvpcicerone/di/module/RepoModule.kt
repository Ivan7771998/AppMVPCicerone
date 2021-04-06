package com.dev777popov.appmvpcicerone.di.module

import com.dev777popov.appmvpcicerone.mvp.api.IDataSource
import com.dev777popov.appmvpcicerone.mvp.model.cache.ICache
import com.dev777popov.appmvpcicerone.mvp.model.network.INetworkStatus
import com.dev777popov.appmvpcicerone.mvp.model.repo.GithubUsersRepo
import com.dev777popov.appmvpcicerone.mvp.model.repo.IGithubUsersRepo
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: ICache
    ): IGithubUsersRepo = GithubUsersRepo(api, networkStatus, cache)
}