package com.minosai.archusers.repo

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.Context
import android.icu.text.IDNA
import android.util.Log
import android.widget.Toast
import com.minosai.archusers.db.CryptoDatabase
import com.minosai.archusers.model.ApiResponse
import com.minosai.archusers.model.CurrencyData
import com.minosai.archusers.network.WebService
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.coroutines.experimental.coroutineContext

/**
 * Created by minos.ai on 10/05/18.
 */

class CryptoRepo(context: Context) {

    private var webService: WebService = WebService.create()
    private var database: CryptoDatabase = CryptoDatabase.getInstance(context)

    fun getCryptos(): LiveData<PagedList<CurrencyData>> {
        val count = database.cryptoDao().getCryptoCount()
        if(count < 1) {
            refreshCryptos()
        }

        val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(true)
                .setPrefetchDistance(10)
                .setPageSize(20).setEnablePlaceholders(false).build()
        return LivePagedListBuilder(database.cryptoDao().getAllCryptos(), pagedListConfig)
                .build()
    }

    fun refreshCryptos() {
        launch(UI) {
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

    fun getCryptoById(id: Int)  = database.cryptoDao().getCryptoById(id)
}