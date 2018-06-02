package com.minosai.archusers.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.minosai.archusers.di.key.ViewModelKey
import com.minosai.archusers.ui.viewmodel.CryptoViewModel
import com.minosai.archusers.ui.viewmodel.FactoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CryptoViewModel::class)
    abstract fun bindCryptoViewModel(cryptoViewModel: CryptoViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: FactoryViewModel): ViewModelProvider.Factory
}