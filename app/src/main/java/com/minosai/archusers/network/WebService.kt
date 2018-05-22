package com.minosai.archusers.network

import com.minosai.archusers.model.ApiResponse
import com.minosai.archusers.model.User
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by minos.ai on 10/05/18.
 */
interface WebService {
    @GET("?structure=array")
    fun fetchAllCryptos(): Deferred<Response<ApiResponse>>

    companion object {
        fun create(): WebService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.coinmarketcap.com/v2/ticker/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(WebService::class.java)
        }
    }
}