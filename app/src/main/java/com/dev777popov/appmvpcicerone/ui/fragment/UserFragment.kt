package com.dev777popov.appmvpcicerone.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev777popov.appmvpcicerone.App
import com.dev777popov.appmvpcicerone.BackClickListener
import com.dev777popov.appmvpcicerone.databinding.FragmentUsersBinding
import com.dev777popov.appmvpcicerone.mvp.model.GithubUserRepo
import com.dev777popov.appmvpcicerone.mvp.presenter.UserPresenter
import com.dev777popov.appmvpcicerone.mvp.view.UsersView
import com.dev777popov.appmvpcicerone.ui.adapter.UserRVAdapter
import com.dev777popov.appmvpcicerone.ui.navigation.AndroidScreens
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UsersView, BackClickListener {

    companion object {
        fun newInstance() = UserFragment()
    }

    private val presenter by moxyPresenter {
        UserPresenter(GithubUserRepo(), App.instance.router, AndroidScreens())
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
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserRVAdapter(presenter = presenter.userListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean = presenter.backClick()


}