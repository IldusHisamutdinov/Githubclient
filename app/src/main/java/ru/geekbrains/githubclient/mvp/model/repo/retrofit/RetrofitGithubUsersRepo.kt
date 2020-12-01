package ru.geekbrains.githubclient.mvp.model.repo.retrofit

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.githubclient.mvp.model.api.IDataSource
import ru.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo

class RetrofitGithubUsersRepo(val api: IDataSource): IGithubUsersRepo {
    override fun getUsers() = api.loadUsers().subscribeOn(Schedulers.io())
    override fun getRepositories(url:String) = api.getRepositories(url).subscribeOn(Schedulers.io())
}