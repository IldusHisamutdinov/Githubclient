package ru.geekbrains.githubclient.mvp.presenters

import moxy.MvpPresenter
import ru.geekbrains.githubclient.mvp.view.MainView
import ru.geekbrains.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}