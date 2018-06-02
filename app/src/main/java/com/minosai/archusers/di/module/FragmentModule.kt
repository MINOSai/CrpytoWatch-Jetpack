package com.minosai.archusers.di.module

import com.minosai.archusers.ui.fragment.InfoFragment
import com.minosai.archusers.ui.fragment.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ListFragment

    @ContributesAndroidInjector
    abstract fun contributeInfoFragment(): InfoFragment
}