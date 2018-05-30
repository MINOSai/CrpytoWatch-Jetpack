package com.minosai.archusers.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.minosai.archusers.model.CurrencyData
import com.minosai.archusers.repo.CryptoRepo
import javax.inject.Inject

/**
 * Created by minos.ai on 16/05/18.
 */
class CryptoViewModel : ViewModel() {

    @Inject lateinit var cryptoRepo: CryptoRepo

//    @Inject
//    constructor(cryptoRepo: CryptoRepo): this() {
//        this.cryptoRepo = cryptoRepo
//    }

    fun getCryptoList() = cryptoRepo.getCryptos()

    fun refreshCryptos() {
        cryptoRepo.refreshCryptos()
    }

    fun getCryptoById(id: Int) = cryptoRepo.getCryptoById(id)

}