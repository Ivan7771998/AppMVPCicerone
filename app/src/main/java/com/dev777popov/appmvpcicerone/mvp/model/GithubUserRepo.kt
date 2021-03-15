package com.dev777popov.appmvpcicerone.mvp.model

import com.dev777popov.appmvpcicerone.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Observable

class GithubUserRepo {

    private val users = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6"),
    )

    fun create() = Observable.create<GithubUser> { emitter ->
        try {
            val list: List<GithubUser> = users
            list.forEach {
                emitter.onNext(it)
            }
        } catch (e: Throwable){
            emitter.onError(e)
        }
        emitter.onComplete()
    }

    fun getUsers() = users
}