package ru.geekbrains.githubclient.mvp.model.entity;

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable

//class GithubUsersRepo {
//    private val repositories = listOf(
//            GithubUser("login1"),
//            GithubUser("login2"),
//            GithubUser("login3"),
//            GithubUser("login4"),
//            GithubUser("login5")
//    )
//
//    fun getUsers(): @NonNull Observable<List<GithubUser>>? {
//        return Observable.fromIterable(listOf(repositories))
//
//    }
//
//    fun getUser(userId: Int): Observable<GithubUser> =
//            Observable.fromIterable(listOf(repositories[userId]))
//
//}