package com.dev777popov.appmvpcicerone.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev777popov.appmvpcicerone.databinding.RvItemRepoBinding
import com.dev777popov.appmvpcicerone.mvp.presenter.RepoUserPresenter
import com.dev777popov.appmvpcicerone.mvp.view.list.IRepoUserItemView

class RepoUserRVAdapter(private val presenter: RepoUserPresenter.RepoUserPresenter) :
    RecyclerView.Adapter<RepoUserRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RvItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount(): Int = presenter.getCount()


    inner class ViewHolder(private val vb: RvItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        IRepoUserItemView {
        override fun setNameRepo(name: String?) {
            vb.repoName.text = name
        }

        override var pos: Int = -1
    }
}