package ru.geekbrains.githubclient

import android.app.Application
import ru.geekbrains.githubclient.di.AppComponent
import ru.geekbrains.githubclient.di.DaggerAppComponent
import ru.geekbrains.githubclient.di.module.AppModule
import ru.geekbrains.githubclient.mvp.model.entity.room.GithubDatabase

class GithubApplication : Application() {
    companion object {
         lateinit var instance: GithubApplication
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
 //       GithubDatabase.create(this)
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
  //      ApiHolder.api

    }
}
