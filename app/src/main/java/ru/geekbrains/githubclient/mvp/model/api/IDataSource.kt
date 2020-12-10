package ru.geekbrains.githubclient.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.entity.ReposGithubUser


interface IDataSource {

    @GET("/users")
    fun loadUsers(): Single<List<GithubUser>>

    @GET
    fun getRepositories(@Url url: String): Single<List<ReposGithubUser>>

}


