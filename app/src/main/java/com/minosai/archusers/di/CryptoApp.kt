package com.minosai.archusers.di

import android.app.Application
import android.content.Context

class CryptoApp : Application() {

    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        initDagger()
        context = applicationContext
    }

    fun initDagger() {
        DaggerCryptoComponent.builder().application(this).build().inject(this)
    }
}