package ru.geekbrains.githubclient.mvp.model.cache.room

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.githubclient.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.entity.room.GithubDatabase
import ru.geekbrains.githubclient.mvp.model.entity.room.RoomGithubUser


class RoomGithubUsersCache(val db: GithubDatabase) : IGithubUsersCache {

    override fun addUsers(users: List<GithubUser>) = Single.fromCallable {
        val roomUsers = users.map {
            RoomGithubUser(
                    it.id ?: "",
                    it.login ?: "",
                    it.avatarUrl ?: "",
                    it.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
        users
    }

    override fun getUsers() = Single.fromCallable {
        db.userDao.getAll().map {
            GithubUser(it.id,
                    it.login,
                    it.avatarUrl,
                    it.reposUrl
            )
        }
    }
}