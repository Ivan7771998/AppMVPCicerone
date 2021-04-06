package com.dev777popov.appmvpcicerone.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.dev777popov.appmvpcicerone.App
import com.dev777popov.appmvpcicerone.BackClickListener
import com.dev777popov.appmvpcicerone.databinding.FragmentUsersBinding
import com.dev777popov.appmvpcicerone.mvp.api.ApiHolder
import com.dev777popov.appmvpcicerone.mvp.model.cache.RoomImageCache
import com.dev777popov.appmvpcicerone.mvp.model.cache.СacheRepo
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.db.Database
import com.dev777popov.appmvpcicerone.mvp.model.repo.GithubUsersRepo
import com.dev777popov.appmvpcicerone.mvp.presenter.UsersPresenter
import com.dev777popov.appmvpcicerone.mvp.view.UsersView
import com.dev777popov.appmvpcicerone.ui.adapter.UserRVAdapter
import com.dev777popov.appmvpcicerone.ui.image.GlideImageLoader
import com.dev777popov.appmvpcicerone.ui.navigation.AndroidScreens
import com.dev777popov.appmvpcicerone.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackClickListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
        ).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentUsersBinding? = null
    private var adapter: UserRVAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUsersBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = UserRVAdapter(
            presenter = presenter.userListPresenter, GlideImageLoader(
                RoomImageCache(Database.getInstance(), App.instance.cacheDir),
                AndroidNetworkStatus(requireContext())
            )
        )
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showProgress() {
        vb?.blockProgress?.progress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        vb?.blockProgress?.progress?.visibility = View.GONE
    }

    override fun backPressed(): Boolean = presenter.backClick()

}