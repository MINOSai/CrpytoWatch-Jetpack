package com.minosai.archusers.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.minosai.archusers.model.CurrencyData
import com.minosai.archusers.repo.CryptoRepo

/**
 * Created by minos.ai on 16/05/18.
 */
class CryptoViewModel(application : Application) : AndroidViewModel(application) {

    val cryptoRepo = CryptoRepo(application)

    fun getCryptoList() = cryptoRepo.getCryptos()

    fun refreshCryptos() {
        cryptoRepo.refreshCryptos()
    }

}