package com.minosai.archusers.ui

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.minosai.archusers.model.CurrencyData
import com.minosai.archusers.repo.CryptoRepo

/**
 * Created by minos.ai on 16/05/18.
 */
class CryptoViewModel(application : Application) : ViewModel() {

    val cryptoRepo = CryptoRepo(application)
    var cryptoList: LiveData<List<CurrencyData>>? = null

    init {
        updateCryptoList()
    }

    fun updateCryptoList() {
        cryptoList = cryptoRepo.getCryptos()
    }

    fun refreshCryptos() {
        cryptoRepo.refreshCryptos()
        updateCryptoList()
    }

}