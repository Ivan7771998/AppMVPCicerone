package com.dev777popov.appmvpcicerone.mvp.model.repo

import com.dev777popov.appmvpcicerone.mvp.api.IDataSource
import com.dev777popov.appmvpcicerone.mvp.api.model.GithubUser
import com.dev777popov.appmvpcicerone.mvp.api.model.RepositoriesUser
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.RoomGithubRepository
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.db.Database
import com.dev777popov.appmvpcicerone.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class GithubRepoUserRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: Database
) : IGithubRepoUserRepo {
    override fun getRepositoriesUser(user: GithubUser): Single<List<RepositoriesUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->

            if (isOnline) {
                user.reposUrl?.let { uri ->
                    api.getRepoUser(uri).flatMap { repositories ->
                        Single.fromCallable {

                            val roomUser =
                                db.userDao.findByLogin(user.login) ?: throw RuntimeException(
                                    "No user in DB"
                                )
                            val roomRepos = repositories.map { repo ->
                                RoomGithubRepository(
                                    repo.id,
                                    repo.name,
                                    repo.forksCount,
                                    repo.owner,
                                    repo.description,
                                    repo.createdAt,
                                    repo.updatedAt,
                                    repo.watchersCount,
                                    repo.language,
                                    repo.defaultBranch,
                                    roomUser.id
                                )
                            }
                            db.repository.insert(roomRepos)
                            repositories
                        }
                    }
                }
                    ?: Single.error<List<RepositoriesUser>>(RuntimeException("User has not repos url"))
            } else {
                Single.fromCallable {
                    val roomUser =
                        db.userDao.findByLogin(user.login) ?: throw RuntimeException(
                            "No user in DB"
                        )
                    db.repository.findForUser(roomUser.id).map { roomRepo ->
                        RepositoriesUser(
                            roomRepo.id,
                            roomRepo.name,
                            roomRepo.forksCount,
                            roomRepo.owner,
                            roomRepo.description,
                            roomRepo.createdAt,
                            roomRepo.updatedAt,
                            roomRepo.watchersCount,
                            roomRepo.language,
                            roomRepo.defaultBranch
                        )
                    }
                }
            }
        }.subscribeOn(Schedulers.io())
}