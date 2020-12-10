package ru.geekbrains.githubclient.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.entity.ReposGithubUser

interface IGithubRepositoriesRepo {
    fun getRepositories(user: GithubUser): Single<List<ReposGithubUser>>
}
