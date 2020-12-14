package ru.geekbrains.githubclient.mvp.model.repo.retrofit

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.githubclient.mvp.model.api.IDataSource
import ru.geekbrains.githubclient.mvp.model.cache.IReposUserCache
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.network.INetworkStatus
import ru.geekbrains.githubclient.mvp.model.repo.IGithubRepositoriesRepo

class RetrofitGithubRepositoriesRepo(
        val api: IDataSource,
        val networkStatus: INetworkStatus,
        val cache: IReposUserCache
): IGithubRepositoriesRepo {

    override fun getRepositories(user:GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if(isOnline) {
                api.getRepositories(user.reposUrl).flatMap { repos ->
                    cache.addReposUser(user, repos)
                }
        } else {
            cache.getReposUser(user)
        }

    }.subscribeOn(Schedulers.io())


}