package ru.geekbrains.githubclient.mvp.presenters

import android.util.Log

import moxy.MvpPresenter
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.entity.GithubUsersRepo
import ru.geekbrains.githubclient.mvp.presenters.list.IUserListPresenter
import ru.geekbrains.githubclient.mvp.view.UserItemView
import ru.geekbrains.githubclient.mvp.view.UsersView
import ru.geekbrains.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router

class UsersPresenter(
        private val usersRepo: GithubUsersRepo,
        private val router: Router
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = {
            Log.v("TAG", "itemClickListener: ${it.pos}")
            router.navigateTo(Screens.LoginScreen(it.pos))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
                ?.subscribe({ onNext ->
                    usersListPresenter.users.addAll(onNext)
                    viewState.updateList()
                },
                        { error -> (Log.e("log", "Error: ${error}")) })
    }
}



