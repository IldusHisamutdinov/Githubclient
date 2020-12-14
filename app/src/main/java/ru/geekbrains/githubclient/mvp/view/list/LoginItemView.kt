package ru.geekbrains.githubclient.mvp.view.list

interface LoginItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}