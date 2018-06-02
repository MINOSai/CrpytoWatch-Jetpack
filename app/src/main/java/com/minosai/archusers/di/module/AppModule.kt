package com.minosai.archusers.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.minosai.archusers.db.CryptoDao
import com.minosai.archusers.db.CryptoDatabase
import com.minosai.archusers.network.WebService
import com.minosai.archusers.repo.CryptoRepo
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
    fun provideWebService(): WebService = Retrofit.Builder()
            .baseUrl("http://api.coinmarketcap.com/v2/ticker/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(WebService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(application: Application): CryptoDatabase {
        return Room.databaseBuilder(
                application,
                CryptoDatabase::class.java,
                "cryptodatabase.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: CryptoDatabase): CryptoDao {
        return database.cryptoDao()
    }
}