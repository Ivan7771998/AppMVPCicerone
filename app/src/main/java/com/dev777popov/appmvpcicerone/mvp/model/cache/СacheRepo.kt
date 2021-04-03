package com.dev777popov.appmvpcicerone.mvp.model.cache

import com.dev777popov.appmvpcicerone.mvp.api.model.GithubUser
import com.dev777popov.appmvpcicerone.mvp.api.model.RepositoriesUser
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.RoomGithubRepository
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.RoomGithubUser
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Single
import java.lang.RuntimeException

class СacheRepo(val db: Database) : ICache {

    override fun putUsers(users: List<GithubUser>): Single<List<GithubUser>> {
        return Single.fromCallable {
            val roomUsers = users.map { user ->
                RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
            }
            db.userDao.insert(roomUsers)
            users
        }
    }

    override fun getUsers(): Single<List<GithubUser>> {
        return Single.fromCallable {
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

    override fun putRepositories(
        login: String,
        repositories: List<RepositoriesUser>
    ): Single<List<RepositoriesUser>> {
        return Single.fromCallable {
            val roomUser =
                db.userDao.findByLogin(login) ?: throw RuntimeException(
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

    override fun getRepositories(login: String): Single<List<RepositoriesUser>> {
       return Single.fromCallable {
            val roomUser =
                db.userDao.findByLogin(login) ?: throw RuntimeException(
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
}