package com.minosai.archusers.repo

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.minosai.archusers.db.CryptoDao
import com.minosai.archusers.model.CurrencyData
import com.minosai.archusers.network.WebService
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject
import javax.inject.Singleton
import com.minosai.archusers.utils.PreferenceHelper.set
import com.minosai.archusers.utils.PreferenceHelper.get

/**
 * Created by minos.ai on 10/05/18.
 */

@Singleton
class CryptoRepo @Inject constructor(val webService: WebService, val dao: CryptoDao) {

    companion object {
        val PREF_WATCHLIST = "com.minosai.archusers.prefs.PREF_WATCHLIST"
    }

    fun getCryptos(): LiveData<PagedList<CurrencyData>> {
        val count = dao.getCryptoCount()
        Log.d("CRYPTO-COUNT", count.toString())
        if(count < 1) {
            refreshCryptos()
        }

        val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(true)
                .setPrefetchDistance(10)
                .setPageSize(20).setEnablePlaceholders(false).build()
        return LivePagedListBuilder(dao.getAllCryptos(), pagedListConfig)
                .build()
    }

    fun refreshCryptos() {
        launch {
            try {
                val request = webService.fetchAllCryptos()
                val response = request.await()
                if(response.isSuccessful) {
                    Log.d("CRYPTO-RESPONSE", response.body()?.data.toString())
                    dao.insertCryptos(response.body()?.data!!)
                }
            } catch (exception: Exception) {

            }
        }
    }

    fun getCryptoById(id: Int)  = dao.getCryptoById(id)
}