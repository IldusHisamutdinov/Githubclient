package ru.geekbrains.githubclient.mvp.presenters

import moxy.MvpPresenter
import ru.geekbrains.githubclient.mvp.model.entity.ReposGithubUser
import ru.geekbrains.githubclient.mvp.view.ReposLoginView
import ru.terrakok.cicerone.Router

class ReposLoginPresenter(val router: Router) : MvpPresenter<ReposLoginView>() {

    var repos: ReposGithubUser? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repos?.let { viewState.init(it) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}