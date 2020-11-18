package ru.geekbrains.githubclient.mvp.presenters

import android.util.Log
import moxy.MvpPresenter
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.entity.GithubUsersRepo
import ru.geekbrains.githubclient.mvp.view.LoginView

class LoginPresenter(private val usersRepo: GithubUsersRepo,) : MvpPresenter<LoginView>() {

    lateinit var theUserData: GithubUser
    var userIndex: Int? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()

    }

    private fun loadData() {
        userIndex?.run {
            usersRepo.getUser(this)
                    .subscribe(
                            { data -> theUserData = data },
                            { error -> (Log.e("log", "Error: ${error}")) },
                            { viewState.init() }
                    )
        } ?: Log.e("log", "Error")

    }

}