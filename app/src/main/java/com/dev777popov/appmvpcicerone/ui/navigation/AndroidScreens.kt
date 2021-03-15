package com.dev777popov.appmvpcicerone.ui.navigation

import com.dev777popov.appmvpcicerone.mvp.navigation.IScreens
import com.dev777popov.appmvpcicerone.ui.fragment.CurrentUserFragment
import com.dev777popov.appmvpcicerone.ui.fragment.UserFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(): Screen = FragmentScreen { UserFragment.newInstance() }
    override fun user(text: String) = FragmentScreen {
        CurrentUserFragment.newInstance(text)
    }
}