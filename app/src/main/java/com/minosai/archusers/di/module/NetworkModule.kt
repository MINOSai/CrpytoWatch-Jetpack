package com.minosai.archusers.di.module

import android.app.Application
import dagger.Module
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.minosai.archusers.network.WebService
import dagger.Provides
import okhttp3.Cache
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit






@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application) = Cache(application.cacheDir, 10 * 1024 * 1024)

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache) = OkHttpClient.Builder().cache(cache).build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) = Retrofit.Builder()
            .baseUrl("http://api.coinmarketcap.com/v2ticker/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideWebService(restAdapter: Retrofit) = restAdapter.create(WebService::class.java)
}