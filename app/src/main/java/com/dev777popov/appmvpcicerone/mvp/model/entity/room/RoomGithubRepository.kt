package com.dev777popov.appmvpcicerone.mvp.model.entity.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.dev777popov.appmvpcicerone.mvp.api.model.Owner

@Entity(foreignKeys = [ForeignKey(
    entity = RoomGithubUser::class,
    parentColumns = ["id"],
    childColumns = ["userId"],
    onDelete = ForeignKey.CASCADE
)])
class RoomGithubRepository(
    @PrimaryKey val id: String,
    val name: String? = null,
    val forksCount: Int? = null,
    @Embedded val owner: Owner? = null,
    val description: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val watchersCount: Int? = null,
    val language: String? = null,
    val defaultBranch: String? = null,
    val userId: String
)