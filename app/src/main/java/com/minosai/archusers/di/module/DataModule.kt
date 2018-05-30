package com.minosai.archusers.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.minosai.archusers.db.CryptoDao
import com.minosai.archusers.db.CryptoDatabase
import com.minosai.archusers.network.WebService
import com.minosai.archusers.repo.CryptoRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): CryptoDatabase = Room.databaseBuilder(
            application,
            CryptoDatabase::class.java,
            "cryptodatabase"
    ).build()

    @Provides
    @Singleton
    fun provideDao(database: CryptoDatabase): CryptoDao = database.cryptoDao()

    @Provides
    @Singleton
    fun provideRepository(webService: WebService, dao: CryptoDao): CryptoRepo = CryptoRepo(webService, dao)
}