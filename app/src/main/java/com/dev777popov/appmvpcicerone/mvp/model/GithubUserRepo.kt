package com.dev777popov.appmvpcicerone.mvp.model

import com.dev777popov.appmvpcicerone.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class GithubUserRepo {

    private val users = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6"),
    )

    fun create(): Single<List<GithubUser>> = Single.create { emitter ->
        try {
            emitter.onSuccess(users)
        } catch (e: Throwable) {
            emitter.onError(e)
        }
    }

    fun getUsers() = users
}