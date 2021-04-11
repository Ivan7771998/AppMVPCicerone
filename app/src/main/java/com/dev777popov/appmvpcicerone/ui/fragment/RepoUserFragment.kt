package com.dev777popov.appmvpcicerone.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev777popov.appmvpcicerone.App
import com.dev777popov.appmvpcicerone.BackClickListener
import com.dev777popov.appmvpcicerone.databinding.FragmentRepoUserBinding
import com.dev777popov.appmvpcicerone.mvp.api.model.GithubUser
import com.dev777popov.appmvpcicerone.mvp.presenter.RepoUserPresenter
import com.dev777popov.appmvpcicerone.mvp.view.RepoUserView
import com.dev777popov.appmvpcicerone.ui.adapter.RepoUserRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoUserFragment : MvpAppCompatFragment(), RepoUserView, BackClickListener {

    companion object {
        private const val EXTRA_DATA = "user"

        fun newInstance(user: GithubUser) = RepoUserFragment().apply {
            arguments = bundleOf(EXTRA_DATA to user)
        }
    }

    private val presenter by moxyPresenter {
        RepoUserPresenter(
            arguments?.getParcelable(EXTRA_DATA),
        ).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentRepoUserBinding? = null
    private var adapter: RepoUserRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRepoUserBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun init() {
        vb?.rvUserRepo?.layoutManager = LinearLayoutManager(requireContext())
        adapter = RepoUserRVAdapter(presenter.repoUserRepoPresenter)
        vb?.rvUserRepo?.adapter = adapter
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

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean = presenter.backClick()
}