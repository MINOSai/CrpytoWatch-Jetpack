package com.minosai.archusers.di.module

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module

@Module
abstract class BaseActivityModule {

    @Binds
    abstract fun activity(appCompatActivity: AppCompatActivity): Activity

    @Binds
    abstract fun activityContext(activity: Activity): Context
}