package com.dev777popov.appmvpcicerone.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev777popov.appmvpcicerone.databinding.ItemUserBinding
import com.dev777popov.appmvpcicerone.mvp.presenter.UserPresenter
import com.dev777popov.appmvpcicerone.mvp.view.list.IUserItemView

class UserRVAdapter(private val presenter: UserPresenter.UserListPresenter) :
    RecyclerView.Adapter<UserRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemUserBinding.inflate(
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


    inner class ViewHolder(val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        IUserItemView {
        override fun setLogin(login: String) {
            vb.tvLogin.text = login
        }

        override var pos: Int = -1

    }


}