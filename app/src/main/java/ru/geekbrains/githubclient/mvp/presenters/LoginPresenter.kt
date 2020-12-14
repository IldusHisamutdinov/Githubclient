package ru.geekbrains.githubclient.mvp.presenters

import android.util.Log
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.githubclient.GithubApplication
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.entity.ReposGithubUser
import ru.geekbrains.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.githubclient.mvp.presenters.list.IReposLoginPresenter
import ru.geekbrains.githubclient.mvp.view.LoginView
import ru.geekbrains.githubclient.mvp.view.list.ReposLoginItemView
import ru.geekbrains.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class LoginPresenter() : MvpPresenter<LoginView>() {
    @Inject
    lateinit var scheduler: Scheduler
    @Inject
    lateinit var usersRepo: IGithubRepositoriesRepo
    @Inject
    lateinit var router: Router
    init {
        GithubApplication.instance.appComponent.inject(this)
    }

  class ReposUserListPresenter: IReposLoginPresenter {
      var reposUserList = mutableListOf<ReposGithubUser>()
      override var itemClickListener: ((ReposLoginItemView) -> Unit)? = null

      override fun bindView(view: ReposLoginItemView) {
          val repos = reposUserList[view.pos]
          repos.name?.let(view::setReposLogin)
      }

      override fun getCount() = reposUserList.size
  }
    val reposUserPresenter = ReposUserListPresenter()
    lateinit var user: GithubUser

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
             usersRepo.getRepositories(user)
                    .observeOn(scheduler)
                    .subscribe({ repos ->
                      reposUserPresenter.reposUserList.clear()
                      reposUserPresenter.reposUserList.addAll(repos)
                      viewState.updateList()
                    }, { error -> (Log.e("log", "Error: ${error}")) })

    }
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}