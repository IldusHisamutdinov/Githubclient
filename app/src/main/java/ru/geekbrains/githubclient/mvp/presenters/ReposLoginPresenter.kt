package ru.geekbrains.githubclient.mvp.presenters

import moxy.MvpPresenter
import ru.geekbrains.githubclient.GithubApplication
import ru.geekbrains.githubclient.mvp.model.entity.ReposGithubUser
import ru.geekbrains.githubclient.mvp.view.ReposLoginView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ReposLoginPresenter() : MvpPresenter<ReposLoginView>() {
    @Inject
    lateinit var router: Router
    init {
        GithubApplication.instance.appComponent.inject(this)
    }
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