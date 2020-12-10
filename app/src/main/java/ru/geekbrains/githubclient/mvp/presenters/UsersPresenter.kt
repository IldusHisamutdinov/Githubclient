package ru.geekbrains.githubclient.mvp.presenters

import android.util.Log
import io.reactivex.rxjava3.core.Scheduler

import moxy.MvpPresenter
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.githubclient.mvp.presenters.list.ILoginListPresenter
import ru.geekbrains.githubclient.mvp.view.list.LoginItemView
import ru.geekbrains.githubclient.mvp.view.UsersView
import ru.geekbrains.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router

class UsersPresenter(
        val mainThreadScheduler: Scheduler,
        val usersRepo: IGithubUsersRepo,
        val router: Router
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : ILoginListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((LoginItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: LoginItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it)}
            user.avatarUrl?.let { view.loadAvatar(it) }
        }

    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = {
            val user: GithubUser = usersListPresenter.users[it.pos]
            router.navigateTo(Screens.LoginScreen(user))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
                .observeOn(mainThreadScheduler)
                .subscribe({ repos ->
                    usersListPresenter.users.clear()
                    usersListPresenter.users.addAll(repos)
                    viewState.updateList()
                }, { error -> (Log.e("log", "Error: ${error}")) })
    }
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}



