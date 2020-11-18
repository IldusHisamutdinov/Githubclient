package ru.geekbrains.githubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_users.*
import ru.geekbrains.githubclient.mvp.view.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.githubclient.GithubApplication
import ru.geekbrains.githubclient.R
import ru.geekbrains.githubclient.mvp.model.entity.GithubUsersRepo
import ru.geekbrains.githubclient.mvp.presenters.UsersPresenter
import ru.geekbrains.githubclient.ui.adapter.UsersRVAdapter


class UsersFragment : MvpAppCompatFragment(), UsersView {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), GithubApplication.instance.router)
    }

    private val adapter: UsersRVAdapter by lazy {
        UsersRVAdapter(presenter.usersListPresenter)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_users.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

}
