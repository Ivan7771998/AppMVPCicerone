package com.dev777popov.appmvpcicerone.di.module

import com.dev777popov.appmvpcicerone.App
import com.dev777popov.appmvpcicerone.mvp.api.IDataSource
import com.dev777popov.appmvpcicerone.mvp.model.network.INetworkStatus
import com.dev777popov.appmvpcicerone.ui.network.AndroidNetworkStatus
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun api(gson: Gson): IDataSource = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(IDataSource::class.java)

    @Provides
    fun gson():Gson =  GsonBuilder()
    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .excludeFieldsWithoutExposeAnnotation()
    .create()

    @Provides
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)
}