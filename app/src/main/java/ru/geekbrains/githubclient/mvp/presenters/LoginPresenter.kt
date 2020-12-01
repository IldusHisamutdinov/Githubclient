package ru.geekbrains.githubclient.mvp.presenters

import android.util.Log
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.entity.ReposGithubUser
import ru.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.githubclient.mvp.presenters.list.IReposLoginPresenter
import ru.geekbrains.githubclient.mvp.view.LoginView
import ru.geekbrains.githubclient.mvp.view.ReposLoginItemView
import ru.geekbrains.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router

class LoginPresenter(val mainThreadScheduler: Scheduler,
                     val usersRepo: IGithubUsersRepo,
                     val router: Router) : MvpPresenter<LoginView>() {

  class ReposUserListPresenter: IReposLoginPresenter {
      var reposUserList = mutableListOf<ReposGithubUser>()
      override var itemClickListener: ((ReposLoginItemView) -> Unit)? = null

      override fun bindView(view: ReposLoginItemView) {
          val repos = reposUserList[view.pos]
          repos.name?.let { view.setReposLogin(it)}
      }

      override fun getCount() = reposUserList.size
  }
    val reposUserPresenter = ReposUserListPresenter()
    var user: GithubUser? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        reposUserPresenter.itemClickListener = {
            val repos = reposUserPresenter.reposUserList[it.pos]
            router.navigateTo(Screens.ReposScreen(repos))
        }

    }

    private fun loadData() {
        user?.reposUrl?.let {
            usersRepo.getRepositories(it)
                    .observeOn(mainThreadScheduler)
                    .subscribe({ repos ->
                      reposUserPresenter.reposUserList.clear()
                      reposUserPresenter.reposUserList.addAll(repos)
                      viewState.updateList()
                    }, { error -> (Log.e("log", "Error: ${error}")) })
        }

    }

}