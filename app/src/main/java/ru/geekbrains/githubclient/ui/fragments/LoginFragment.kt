package ru.geekbrains.githubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_login.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.githubclient.ApiHolder
import ru.geekbrains.githubclient.GithubApplication
import ru.geekbrains.githubclient.R
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import ru.geekbrains.githubclient.mvp.presenters.LoginPresenter
import ru.geekbrains.githubclient.mvp.view.LoginView
import ru.geekbrains.githubclient.ui.adapter.ReposLoginAdapter

class LoginFragment : MvpAppCompatFragment(), LoginView {

    companion object {
        private const val USER_INDEX = "user_index"
        fun newInstance(user: GithubUser): MvpAppCompatFragment {
            val fragment = LoginFragment()
            val bundle = Bundle()
            bundle.putParcelable(USER_INDEX, user)
            fragment.arguments = bundle
            return fragment
        }
    }

    val presenter: LoginPresenter by moxyPresenter {
        LoginPresenter(
                AndroidSchedulers.mainThread(),
                RetrofitGithubUsersRepo(ApiHolder.api),
                GithubApplication.instance.router
        )
    }
    private var user: GithubUser? = null
    private var adapter: ReposLoginAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val view = View.inflate(context, R.layout.fragment_login, null)
        presenter.user = arguments?.getParcelable(USER_INDEX)
        user = arguments?.getParcelable(USER_INDEX)
        return view
    }

    override fun init() {
        activity?.title = user?.login
        repos_login.layoutManager = LinearLayoutManager(context)
        adapter = ReposLoginAdapter(presenter.reposUserPresenter)
        repos_login.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

}