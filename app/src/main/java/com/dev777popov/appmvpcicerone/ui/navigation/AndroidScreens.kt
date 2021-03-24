package com.dev777popov.appmvpcicerone.ui.navigation

import com.dev777popov.appmvpcicerone.mvp.api.model.GithubUser
import com.dev777popov.appmvpcicerone.mvp.api.model.RepositoriesUser
import com.dev777popov.appmvpcicerone.mvp.navigation.IScreens
import com.dev777popov.appmvpcicerone.ui.fragment.CurrentRepoFragment
import com.dev777popov.appmvpcicerone.ui.fragment.RepoUserFragment
import com.dev777popov.appmvpcicerone.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(): Screen = FragmentScreen { UsersFragment.newInstance() }
    override fun user(user: GithubUser): Screen = FragmentScreen {
        RepoUserFragment.newInstance(user)
    }

    override fun repo(repo: RepositoriesUser): Screen = FragmentScreen {
        CurrentRepoFragment.newInstance(repo)
    }
}