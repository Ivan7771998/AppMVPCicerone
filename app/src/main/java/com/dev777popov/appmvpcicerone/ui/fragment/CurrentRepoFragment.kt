package com.dev777popov.appmvpcicerone.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import com.dev777popov.appmvpcicerone.App
import com.dev777popov.appmvpcicerone.BackClickListener
import com.dev777popov.appmvpcicerone.databinding.FragmentCurrentRepoUserBinding
import com.dev777popov.appmvpcicerone.mvp.api.model.RepositoriesUser
import com.dev777popov.appmvpcicerone.mvp.model.cache.RoomImageCache
import com.dev777popov.appmvpcicerone.mvp.model.entity.room.db.Database
import com.dev777popov.appmvpcicerone.mvp.presenter.CurrentRepoPresenter
import com.dev777popov.appmvpcicerone.mvp.view.CurrentRepoView
import com.dev777popov.appmvpcicerone.ui.image.GlideImageLoader
import com.dev777popov.appmvpcicerone.ui.image.IImageLoader
import com.dev777popov.appmvpcicerone.ui.network.AndroidNetworkStatus
import com.dev777popov.appmvpcicerone.util.Helper
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CurrentRepoFragment : MvpAppCompatFragment(), CurrentRepoView, BackClickListener {

    companion object {
        private const val EXTRA_DATA_REPO = "repo"

        fun newInstance(repo: RepositoriesUser) = CurrentRepoFragment().apply {
            arguments = bundleOf(EXTRA_DATA_REPO to repo)
        }
    }

    private val presenter by moxyPresenter {
        CurrentRepoPresenter(arguments?.getParcelable(EXTRA_DATA_REPO), App.instance.router)
    }

    private var vb: FragmentCurrentRepoUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCurrentRepoUserBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun setMainInfo(
        name: String?,
        login: String?,
        avatar_url: String?,
        description: String?
    ) {
        vb?.apply {
            val imageLoader: IImageLoader<ImageView> = GlideImageLoader(
                RoomImageCache(Database.getInstance(), App.instance.cacheDir),
                AndroidNetworkStatus(requireContext())
            )
            imageLoader.loadInto(avatar_url.toString(), blockUser.ivAvatar)
            blockUser.tvLogin.text = login
            repoName.text = name
            repoDescription.text = description
        }
    }

    override fun setDateInfo(createdAt: String?, updatedAt: String?) {
        vb?.apply {
            created.text = Helper.getDate(createdAt)
            updated.text = Helper.getDate(updatedAt)
        }
    }

    override fun setAdditionalInfo(
        watchersCount: Int?,
        forksCount: Int?,
        language: String?,
        defaultBranch: String?
    ) {
        vb?.apply {
            repoLanguage.text = language ?: "-"
            repoDefaultBranch.text = defaultBranch
            repoForksCount.text = forksCount.toString()
            repoWatchCount.text = watchersCount.toString()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean = presenter.backClick()

}