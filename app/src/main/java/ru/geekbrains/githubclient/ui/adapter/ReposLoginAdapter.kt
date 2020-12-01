package ru.geekbrains.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repos_user.view.*
import ru.geekbrains.githubclient.R
import ru.geekbrains.githubclient.mvp.presenters.list.IReposLoginPresenter
import ru.geekbrains.githubclient.mvp.view.ReposLoginItemView

class ReposLoginAdapter(val presenter: IReposLoginPresenter) : RecyclerView.Adapter<ReposLoginAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposLoginAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repos_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()


    inner class ViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer, ReposLoginItemView {

        override var pos: Int = -1

        override fun setReposLogin(login: String) = with(containerView) {
            name_repos.text = login
        }
    }
}