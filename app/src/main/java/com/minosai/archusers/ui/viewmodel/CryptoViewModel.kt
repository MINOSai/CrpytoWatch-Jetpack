package com.minosai.archusers.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.minosai.archusers.repo.CryptoRepo
import javax.inject.Inject
import com.minosai.archusers.utils.PreferenceHelper.set
import com.minosai.archusers.utils.PreferenceHelper.get

/**
 * Created by minos.ai on 16/05/18.
 */

class CryptoViewModel @Inject constructor(val cryptoRepo: CryptoRepo, val gson: Gson, val preferences: SharedPreferences): ViewModel() {
    val PREF_WATCHLIST = "com.minosai.archusers.prefs.PREF_WATCHLIST"

    var watchList: MutableLiveData<ArrayList<Int>> = MutableLiveData()

    init {
        watchList.value = getWatchList()
    }

    private fun getWatchList(): ArrayList<Int>? {
        val jsonString: String? = preferences[PREF_WATCHLIST]
        jsonString?.let { json ->
            Log.d("PREF_WATCHLIST", gson.fromJson(json, ArrayList<Int>()::class.java).toString())
            return gson.fromJson(json, ArrayList<Int>()::class.java)
        }
        return null
    }

    fun addToWatchList(id: Int) {
        var list = getWatchList()
        list?.let {
            if (id !in it.toIntArray()) {
                it.add(id)
            }
        } ?: run {
            list = arrayListOf(id)
        }
        preferences[PREF_WATCHLIST] = gson.toJson(list)
        watchList.value = getWatchList()
    }

    fun getCryptoList() = cryptoRepo.getCryptos()

    fun getCryptoById(id: Int) = cryptoRepo.getCryptoById(id)

    fun refresh() = cryptoRepo.refreshCryptos()

}