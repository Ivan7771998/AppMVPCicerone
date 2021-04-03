package com.dev777popov.appmvpcicerone.mvp.model.entity.room.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1_2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `RoomCacheImage` (" +
                "`id` TEXT NOT NULL, " +
                "`localPath` TEXT, " +
                "PRIMARY KEY(`id`))")
    }
}