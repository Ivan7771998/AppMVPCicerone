package com.dev777popov.appmvpcicerone.mvp.navigation

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(text: String): Screen
}