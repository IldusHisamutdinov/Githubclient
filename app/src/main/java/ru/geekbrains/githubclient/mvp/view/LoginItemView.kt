package ru.geekbrains.githubclient.mvp.view

interface LoginItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}