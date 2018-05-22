package com.minosai.archusers.repo

import android.arch.lifecycle.LiveData
import android.content.Context
import com.minosai.archusers.db.CryptoDatabase
import com.minosai.archusers.model.CurrencyData
import com.minosai.archusers.network.WebService
import kotlinx.coroutines.experimental.launch

/**
 * Created by minos.ai on 10/05/18.
 */

class CryptoRepo(context: Context) {

    private var webService: WebService = WebService.create()
    private var database: CryptoDatabase = CryptoDatabase.getInstance(context)

    fun getCryptos(): LiveData<List<CurrencyData>> {
        val count = database.cryptoDao().getCryptoCount()
        if(count < 1) {
            refreshCryptos()
        }
        return database.cryptoDao().getAllCryptos()
    }

    fun refreshCryptos() {
        launch {
            try {
                val request = webService.fetchAllCryptos()
                val response = request.await()
                if(response.isSuccessful) {
                    database.cryptoDao().insertCryptos(response.body()?.data!!)
                }
            } catch (exception: Exception) {

            }
        }
    }
}