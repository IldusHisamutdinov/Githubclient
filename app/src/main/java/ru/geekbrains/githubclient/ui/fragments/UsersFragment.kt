package ru.geekbrains.githubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.coroutines.InternalCoroutinesApi
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.githubclient.ApiHolder
import ru.geekbrains.githubclient.GithubApplication
import ru.geekbrains.githubclient.R
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import ru.geekbrains.githubclient.mvp.model.entity.room.GithubDatabase
import ru.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import ru.geekbrains.githubclient.mvp.presenters.UsersPresenter
import ru.geekbrains.githubclient.mvp.view.UsersView
import ru.geekbrains.githubclient.mvp.view.image.GlideImageLoader
import ru.geekbrains.githubclient.ui.BackButtonListener
import ru.geekbrains.githubclient.ui.adapter.UsersRVAdapter
import ru.geekbrains.githubclient.ui.network.AndroidNetworkStatus



class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }


    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
                AndroidSchedulers.mainThread(),
                RetrofitGithubUsersRepo(ApiHolder.api, AndroidNetworkStatus(GithubApplication.instance.baseContext),
                RoomGithubUsersCache(GithubDatabase.newInstance() as GithubDatabase)),
                GithubApplication.instance.router
        )

    }

    var adapter: UsersRVAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_users, null)


    override fun init() {
        rv_users.layoutManager =
                LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        rv_users.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean = presenter.backPressed()

}
