package ru.geekbrains.githubclient.mvp.model.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser

interface IGithubUsersCache {
    fun getUsers(): Single<List<GithubUser>>
    fun addUsers(users: List<GithubUser>): Single<List<GithubUser>>
}