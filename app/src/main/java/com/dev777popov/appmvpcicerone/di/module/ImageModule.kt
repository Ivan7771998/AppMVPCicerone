package com.dev777popov.appmvpcicerone.di.module

import android.widget.ImageView
import com.dev777popov.appmvpcicerone.App
import com.dev777popov.appmvpcicerone.mvp.model.cache.IImageCache
import com.dev777popov.appmvpcicerone.mvp.model.cache.RoomImageCache
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.db.Database
import com.dev777popov.appmvpcicerone.mvp.model.network.INetworkStatus
import com.dev777popov.appmvpcicerone.ui.image.GlideImageLoader
import com.dev777popov.appmvpcicerone.ui.image.IImageLoader
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageModule {

    @Named("cacheDir")
    @Singleton
    @Provides
    fun cacheDir(app: App): File = app.cacheDir

    @Singleton
    @Provides
    fun imageCache(database: Database, @Named("cacheDir") cacheDir: File): IImageCache =
        RoomImageCache(database, cacheDir)

    @Singleton
    @Provides
    fun imageLoader(cache: IImageCache, networkStatus: INetworkStatus): IImageLoader<ImageView> =
        GlideImageLoader(cache, networkStatus)
}