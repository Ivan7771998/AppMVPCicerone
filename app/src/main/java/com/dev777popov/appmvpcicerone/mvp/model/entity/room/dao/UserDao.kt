package com.dev777popov.appmvpcicerone.mvp.model.entity.room.dao

import androidx.room.*
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.RoomGithubUser

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubUser>)

    @Update
    fun update(user: RoomGithubUser)

    @Update
    fun update(vararg users: RoomGithubUser)

    @Update
    fun update(users: List<RoomGithubUser>)

    @Delete
    fun delete(user: RoomGithubUser)

    @Delete
    fun delete(vararg users: RoomGithubUser)

    @Delete
    fun delete(users: List<RoomGithubUser>)

    @Query("Select * from RoomGithubUser")
    fun getAll(): List<RoomGithubUser>

    @Query("Select * from RoomGithubUser where login = :login LiMIT 1")
    fun findByLogin(login: String):  RoomGithubUser
}