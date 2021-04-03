package com.dev777popov.appmvpcicerone.mvp.model.cache

import com.dev777popov.appmvpcicerone.mvp.api.model.GithubUser
import com.dev777popov.appmvpcicerone.mvp.api.model.RepositoriesUser

import io.reactivex.rxjava3.core.Single

interface ICache {
    fun putUsers(users: List<GithubUser>): Single<List<GithubUser>>
    fun getUsers(): Single<List<GithubUser>>

    fun putRepositories(login: String, repositories: List<RepositoriesUser>): Single<List<RepositoriesUser>>
    fun getRepositories(login: String):  Single<List<RepositoriesUser>>
}