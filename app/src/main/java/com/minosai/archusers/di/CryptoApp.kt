package com.minosai.archusers.di

import android.app.Application
import android.content.Context
import com.minosai.archusers.di.module.AppModule
import com.minosai.archusers.di.module.DataModule
import com.minosai.archusers.di.module.NetworkModule

class CryptoApp : Application() {

    companion object {
        lateinit var cryptoComponent: CryptoComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    fun initDagger() {
//        DaggerCryptoComponent.builder().application(this).build().inject(this)
        cryptoComponent = DaggerCryptoComponent.builder()
                .build()
    }
}