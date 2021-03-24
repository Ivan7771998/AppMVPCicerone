package com.dev777popov.appmvpcicerone.mvp.model.repo

import com.dev777popov.appmvpcicerone.mvp.api.IDataSource
import com.dev777popov.appmvpcicerone.mvp.api.model.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo(private val api: IDataSource): IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> = api.getUsers().subscribeOn(Schedulers.io())
}
