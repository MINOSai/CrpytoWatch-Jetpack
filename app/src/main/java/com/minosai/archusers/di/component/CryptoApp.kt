package com.minosai.archusers.di.component

import android.app.Activity
import android.app.Application
import com.minosai.archusers.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CryptoApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
//        initDagger()
        AppInjector.init(this)
    }

//    private fun initDagger() {
//        DaggerCryptoComponent.builder()
//                .application(this)
//                .build()
//                .inject(this)
//    }
}