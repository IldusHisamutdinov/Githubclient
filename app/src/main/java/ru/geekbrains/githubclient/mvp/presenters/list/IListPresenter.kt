package ru.geekbrains.githubclient.mvp.presenters.list

import ru.geekbrains.githubclient.mvp.view.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

