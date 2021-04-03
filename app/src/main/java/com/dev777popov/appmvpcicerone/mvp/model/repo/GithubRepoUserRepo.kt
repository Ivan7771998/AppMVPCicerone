package com.dev777popov.appmvpcicerone.mvp.model.repo

import com.dev777popov.appmvpcicerone.mvp.api.IDataSource
import com.dev777popov.appmvpcicerone.mvp.api.model.GithubUser
import com.dev777popov.appmvpcicerone.mvp.api.model.RepositoriesUser
import com.dev777popov.appmvpcicerone.mvp.model.cache.ICache
import com.dev777popov.appmvpcicerone.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class GithubRepoUserRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: ICache
) : IGithubRepoUserRepo {
    override fun getRepositoriesUser(user: GithubUser): Single<List<RepositoriesUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->

            if (isOnline) {
                user.reposUrl?.let { uri ->
                    api.getRepoUser(uri).flatMap { repositories ->
                       cache.putRepositories(user.login, repositories)
                    }
                }?: Single.error(RuntimeException("User has not repos url"))
            } else {
                cache.getRepositories(user.login)
            }
        }.subscribeOn(Schedulers.io())
}