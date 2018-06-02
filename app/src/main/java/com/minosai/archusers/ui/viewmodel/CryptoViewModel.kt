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
class CryptoViewModel @Inject constructor(val cryptoRepo: CryptoRepo): ViewModel() {

    fun getCryptoList() = cryptoRepo.getCryptos()

    fun refreshCryptos() {
        cryptoRepo.refreshCryptos()
    }

    fun getCryptoById(id: Int) = cryptoRepo.getCryptoById(id)

}