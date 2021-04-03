package com.dev777popov.appmvpcicerone.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.RoomCacheImg
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.RoomGithubRepository
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.RoomGithubUser
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.dao.ImageDao
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.dao.RepositoryDao
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.dao.UserDao
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.migrations.Migration1_2
import java.lang.IllegalStateException

@androidx.room.Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepository::class,
        RoomCacheImg::class
    ],
    version = 2
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repository: RepositoryDao
    abstract val imageDao: ImageDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null

        fun getInstance() = instance ?: throw IllegalStateException("Database has not been created")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME)
                    .addMigrations(Migration1_2())
                    .build()
            }
        }
    }
}