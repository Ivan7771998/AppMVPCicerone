package com.dev777popov.appmvpcicerone.mvp.model.entity.room.dao

import androidx.room.*
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.RoomCacheImg

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: RoomCacheImg)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg images: RoomCacheImg)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(images: List<RoomCacheImg>)

    @Update
    fun update(image: RoomCacheImg)

    @Update
    fun update(vararg images: RoomCacheImg)

    @Update
    fun update(images: List<RoomCacheImg>)

    @Delete
    fun delete(image: RoomCacheImg)

    @Delete
    fun delete(vararg images: RoomCacheImg)

    @Delete
    fun delete(images: List<RoomCacheImg>)

    @Query("Delete from RoomCacheImg")
    fun clear()

    @Query("Select * from RoomCacheImg")
    fun getAll(): List<RoomCacheImg>

    @Query("Select * from RoomCacheImg where url = :url LiMIT 1")
    fun findByUrl(url: String): RoomCacheImg?
}