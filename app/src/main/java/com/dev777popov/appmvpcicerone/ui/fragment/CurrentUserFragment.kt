package com.dev777popov.appmvpcicerone.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.dev777popov.appmvpcicerone.App
import com.dev777popov.appmvpcicerone.BackClickListener
import com.dev777popov.appmvpcicerone.databinding.CurrentUserFragmentBinding
import com.dev777popov.appmvpcicerone.mvp.model.entity.GithubUser
import com.dev777popov.appmvpcicerone.mvp.presenter.CurrentUserPresenter
import com.dev777popov.appmvpcicerone.mvp.view.CurrentUserView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CurrentUserFragment : MvpAppCompatFragment(), CurrentUserView, BackClickListener {

    companion object {
        private const val EXTRA_DATA = "user"

        fun newInstance(user: GithubUser) = CurrentUserFragment().apply {
            arguments = bundleOf(EXTRA_DATA to user)
        }
    }

    private val presenter by moxyPresenter {
        CurrentUserPresenter(arguments?.getParcelable(EXTRA_DATA), App.instance.router)
    }

    private var vb: CurrentUserFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = CurrentUserFragmentBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean = presenter.backClick()

    override fun setLoginText(txtLogin: String) {
        vb?.txtLogin?.text = txtLogin
    }

}