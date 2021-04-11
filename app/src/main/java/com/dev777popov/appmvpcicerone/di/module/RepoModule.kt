package com.dev777popov.appmvpcicerone.di.module

import com.dev777popov.appmvpcicerone.mvp.api.IDataSource
import com.dev777popov.appmvpcicerone.mvp.model.cache.ICache
import com.dev777popov.appmvpcicerone.mvp.model.network.INetworkStatus
import com.dev777popov.appmvpcicerone.mvp.model.repo.GithubRepoUserRepo
import com.dev777popov.appmvpcicerone.mvp.model.repo.GithubUsersRepo
import com.dev777popov.appmvpcicerone.mvp.model.repo.IGithubRepoUserRepo
import com.dev777popov.appmvpcicerone.mvp.model.repo.IGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: ICache
    ): IGithubUsersRepo = GithubUsersRepo(api, networkStatus, cache)


    @Provides
    @Singleton
    fun repoUsersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: ICache
    ): IGithubRepoUserRepo = GithubRepoUserRepo(api, networkStatus, cache)
}