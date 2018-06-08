package com.minosai.archusers.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.SharedPreferences
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.minosai.archusers.db.CryptoDao
import com.minosai.archusers.db.CryptoDatabase
import com.minosai.archusers.network.WebService
import com.minosai.archusers.repo.CryptoRepo
import com.minosai.archusers.utils.PreferenceHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun providePreferences(application: Application): SharedPreferences = PreferenceHelper.defaultPrefs(application)

    @Provides
    @Singleton
    fun provideWebService(): WebService = Retrofit.Builder()
            .baseUrl("http://api.coinmarketcap.com/v2/ticker/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(WebService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(application: Application): CryptoDatabase = Room.databaseBuilder(
            application,
            CryptoDatabase::class.java,
            "cryptodatabase.db"
    ).build()

    @Provides
    @Singleton
    fun provideDao(database: CryptoDatabase): CryptoDao = database.cryptoDao()
}