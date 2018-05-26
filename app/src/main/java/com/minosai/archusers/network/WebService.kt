package com.minosai.archusers.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.minosai.archusers.model.ApiResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by minos.ai on 10/05/18.
 */
interface WebService {

    @GET("v2/ticker/?structure=array")
    fun fetchAllCryptos(): Deferred<Response<ApiResponse>>

    companion object {
        fun create(): WebService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://api.coinmarketcap.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()

            return retrofit.create(WebService::class.java)
        }
    }
}