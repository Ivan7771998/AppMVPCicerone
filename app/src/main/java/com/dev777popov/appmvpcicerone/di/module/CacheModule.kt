package com.dev777popov.appmvpcicerone.di.module

import androidx.room.Room
import com.dev777popov.appmvpcicerone.App
import com.dev777popov.appmvpcicerone.mvp.api.IDataSource
import com.dev777popov.appmvpcicerone.mvp.model.cache.ICache
import com.dev777popov.appmvpcicerone.mvp.model.cache.СacheRepo
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.RoomCacheImg
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.RoomGithubUser
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.db.Database
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.migrations.Migration1_2
import com.dev777popov.appmvpcicerone.mvp.model.network.INetworkStatus
import com.dev777popov.appmvpcicerone.mvp.model.repo.GithubUsersRepo
import com.dev777popov.appmvpcicerone.mvp.model.repo.IGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .addMigrations(Migration1_2())
        .build()

    @Provides
    @Singleton
    fun usersCache(db: Database): ICache = СacheRepo(db)
}