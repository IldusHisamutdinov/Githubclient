package ru.geekbrains.githubclient.mvp.model.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.entity.ReposGithubUser

interface IReposUserCache {
    fun addReposUser(user: GithubUser, users: List<ReposGithubUser>): Single<List<ReposGithubUser>>
    fun getReposUser(user: GithubUser): Single<List<ReposGithubUser>>

}