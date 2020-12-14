package ru.geekbrains.githubclient.di

import dagger.Component
import ru.geekbrains.githubclient.MainActivity
import ru.geekbrains.githubclient.di.module.*
import ru.geekbrains.githubclient.mvp.presenters.LoginPresenter
import ru.geekbrains.githubclient.mvp.presenters.MainPresenter
import ru.geekbrains.githubclient.mvp.presenters.ReposLoginPresenter
import ru.geekbrains.githubclient.mvp.presenters.UsersPresenter
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ApiModule::class,
            AppModule::class,
            CacheModule::class,
            CiceroneModule::class,
            RepoModule::class
        ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(loginPresenter: LoginPresenter)
    fun inject(reposLoginPresenter: ReposLoginPresenter)

  //  fun inject(fragment: ReposFragment)

}