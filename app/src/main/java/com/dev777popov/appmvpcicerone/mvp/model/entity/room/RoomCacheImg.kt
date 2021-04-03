package com.dev777popov.appmvpcicerone.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomCacheImg(
    @PrimaryKey val url: String,
    val localPath: String
)
