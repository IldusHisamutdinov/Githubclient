package ru.geekbrains.githubclient.mvp.model.cache.room

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.githubclient.mvp.model.cache.IReposUserCache
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.entity.ReposGithubUser
import ru.geekbrains.githubclient.mvp.model.entity.room.GithubDatabase
import ru.geekbrains.githubclient.mvp.model.entity.room.RoomGithubRepository

class RoomRepositoriesCache(val db: GithubDatabase) : IReposUserCache {
    override fun addReposUser(user: GithubUser, users: List<ReposGithubUser>) = Single.fromCallable {
        val roomUser = db.userDao.findByLogin(user.login)
        val roomRepos = users.map {
            RoomGithubRepository(
                    it.id ?: "",
                    it.name ?: "",
                    it.forks ?: 0,
                    roomUser?.id ?: "",
                    it.description ?: ""
            )
        }
        db.repositoryDao.insert(roomRepos)
        users
    }

    override fun getReposUser(user: GithubUser): Single<List<ReposGithubUser>> = Single.fromCallable {
        val roomUser = db.userDao.findByLogin(user.login)
        roomUser?.run {
            db.repositoryDao.findForUser(roomUser.id).map {
                ReposGithubUser(
                        it.id,
                        it.name,
                        it.forks,
                        it.description

                )
            }

        }
    }
}