package com.dev777popov.appmvpcicerone.ui.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}