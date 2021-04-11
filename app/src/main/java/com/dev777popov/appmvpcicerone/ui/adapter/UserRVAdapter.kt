package com.dev777popov.appmvpcicerone.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.dev777popov.appmvpcicerone.databinding.RvItemUserBinding
import com.dev777popov.appmvpcicerone.mvp.presenter.UsersPresenter
import com.dev777popov.appmvpcicerone.mvp.view.list.IUserItemView
import com.dev777popov.appmvpcicerone.ui.image.IImageLoader
import javax.inject.Inject

class UserRVAdapter(private val presenter: UsersPresenter.UsersListPresenter) :
    RecyclerView.Adapter<UserRVAdapter.ViewHolder>() {

    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RvItemUserBinding.inflate(
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


    inner class ViewHolder(private val vb: RvItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        IUserItemView {
        override fun setLogin(login: String?) {
            vb.blockUser.tvLogin.text = login
        }

        override fun loadAvatar(url: String?) {
            if (url != null) {
                imageLoader.loadInto(url, vb.blockUser.ivAvatar)
            }
        }

        override var pos: Int = -1
    }
}