package ru.geekbrains.githubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.githubclient.R
import ru.geekbrains.githubclient.mvp.model.entity.GithubUsersRepo
import ru.geekbrains.githubclient.mvp.presenters.LoginPresenter
import ru.geekbrains.githubclient.mvp.view.LoginView

class LoginFragment : MvpAppCompatFragment(), LoginView {

    companion object {
        private const val USER_INDEX = "user_index"

        fun newInstance(userId: Int) = LoginFragment().apply {
            arguments = Bundle().apply {
                putInt(USER_INDEX, userId)
            }
        }
    }

    private val presenter: LoginPresenter by moxyPresenter {
        LoginPresenter(GithubUsersRepo())
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_login, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.userIndex = arguments?.getInt(USER_INDEX) ?: 0
    }

    override fun init() {
        tv_login.text = presenter.theUserData.login
    }

}