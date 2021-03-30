package com.dev777popov.appmvpcicerone.mvp.model.repo

import com.dev777popov.appmvpcicerone.mvp.api.IDataSource
import com.dev777popov.appmvpcicerone.mvp.api.model.RepositoriesUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubRepoUserRepo(private val api: IDataSource) : IGithubRepoUserRepo {
    override fun getRepositoriesUser(uri: String): Single<List<RepositoriesUser>> =
        api.getRepoUser(uri).subscribeOn(
            Schedulers.io()
        )
}