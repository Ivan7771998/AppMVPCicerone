package com.dev777popov.appmvpcicerone.mvp.model.repo

import com.dev777popov.appmvpcicerone.mvp.api.model.GithubUser
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(scheduler: Scheduler): Single<List<GithubUser>>
}