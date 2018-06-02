package com.minosai.archusers.repo

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.Context
import android.icu.text.IDNA
import android.util.Log
import android.widget.Toast
import com.minosai.archusers.db.CryptoDao
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
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.experimental.coroutineContext

/**
 * Created by minos.ai on 10/05/18.
 */

@Singleton
class CryptoRepo @Inject constructor(val webService: WebService, val dao: CryptoDao) {

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
        Executors.newSingleThreadExecutor().execute {
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
    }

    fun getCryptoById(id: Int)  = dao.getCryptoById(id)
}