package com.minosai.archusers.di

import android.app.Application
import com.minosai.archusers.di.module.AppModule
import com.minosai.archusers.di.module.DataModule
import com.minosai.archusers.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (DataModule::class), (NetworkModule::class)])
interface CryptoComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder
        fun build() : CryptoComponent
    }

    fun inject(cryptoApp: CryptoApp)
}