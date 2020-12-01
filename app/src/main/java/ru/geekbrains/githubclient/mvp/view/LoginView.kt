package ru.geekbrains.githubclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface LoginView: MvpView {
    fun init()
    fun updateList()
}