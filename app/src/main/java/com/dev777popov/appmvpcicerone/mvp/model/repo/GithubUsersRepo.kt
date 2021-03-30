package com.dev777popov.appmvpcicerone.mvp.model.repo

import com.dev777popov.appmvpcicerone.mvp.api.IDataSource
import com.dev777popov.appmvpcicerone.mvp.api.model.GithubUser
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.RoomGithubUser
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.db.Database
import com.dev777popov.appmvpcicerone.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: Database
) :
    IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers().flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
                        }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
            } else {
                Single.fromCallable {
                    db.userDao.getAll().map { roomUser ->

                        GithubUser(
                            roomUser.id,
                            roomUser.login,
                            roomUser.avatarUrl,
                            roomUser.reposUrl
                        )
                    }
                }
            }
        }.subscribeOn(Schedulers.io())

}
