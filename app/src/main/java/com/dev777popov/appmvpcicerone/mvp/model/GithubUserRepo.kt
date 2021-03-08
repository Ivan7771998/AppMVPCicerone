package com.dev777popov.appmvpcicerone.mvp.model

import com.dev777popov.appmvpcicerone.mvp.model.entity.GithubUser

class GithubUserRepo {

    private val users = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6"),
    )

    fun getUsers() = users
}