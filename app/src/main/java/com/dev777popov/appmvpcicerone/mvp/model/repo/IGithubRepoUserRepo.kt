package com.dev777popov.appmvpcicerone.mvp.model.repo

import com.dev777popov.appmvpcicerone.mvp.api.model.GithubUser
import com.dev777popov.appmvpcicerone.mvp.api.model.RepositoriesUser
import io.reactivex.rxjava3.core.Single

interface IGithubRepoUserRepo {
    fun getRepositoriesUser(user: GithubUser): Single<List<RepositoriesUser>>
}