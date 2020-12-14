package ru.geekbrains.githubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_repos.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.githubclient.R
import ru.geekbrains.githubclient.mvp.model.entity.ReposGithubUser
import ru.geekbrains.githubclient.mvp.presenters.ReposLoginPresenter
import ru.geekbrains.githubclient.mvp.view.ReposLoginView
import ru.geekbrains.githubclient.ui.BackButtonListener

class ReposFragment : MvpAppCompatFragment(), ReposLoginView, BackButtonListener {

    companion object {
        const val REPOS_LOGIN = "reposLogin"
        fun newInstance(repos: ReposGithubUser): ReposFragment {
            val fragment = ReposFragment()
            val bundle = Bundle()
            bundle.putParcelable(REPOS_LOGIN, repos)
            fragment.arguments = bundle
            return fragment
        }
    }

    val presenter by moxyPresenter {
        ReposLoginPresenter()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_repos, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.repos = arguments?.getParcelable(REPOS_LOGIN)
    }

    override fun init(reposGithubUser: ReposGithubUser) {
        name.text = reposGithubUser.name
        forks_repos.text = reposGithubUser.forks.toString()
        description_repos.text = reposGithubUser.description
    }

    override fun backPressed() = presenter.backPressed()

}