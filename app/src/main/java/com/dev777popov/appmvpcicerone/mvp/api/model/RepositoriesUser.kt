package com.dev777popov.appmvpcicerone.mvp.api.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoriesUser(
    @Expose val name: String? = null,
    @Expose val forksCount: Int? = null,
    @Expose val owner: Owner? = null,
    @Expose val description: String? = null,
    @Expose val createdAt: String? = null,
    @Expose val updatedAt: String? = null,
    @Expose val watchersCount: Int? = null,
    @Expose val language: String? = null,
    @Expose val defaultBranch: String? = null,
) : Parcelable
