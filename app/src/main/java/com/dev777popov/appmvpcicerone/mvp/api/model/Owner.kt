package com.dev777popov.appmvpcicerone.mvp.api.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    @Expose val avatar_url: String? = null,
    @Expose val followers_url: String? = null,
    @Expose val following_url: String? = null,
    @Expose val login: String? = null,
    @Expose val type: String? = null
) : Parcelable