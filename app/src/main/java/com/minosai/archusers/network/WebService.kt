package com.minosai.archusers.network

import com.minosai.archusers.model.ApiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by minos.ai on 10/05/18.
 */
interface WebService {

    @GET("v2/ticker/?structure=array")
    fun fetchAllCryptos(): Call<ApiResponse>

    companion object {
        fun create(): WebService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://api.coinmarketcap.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(WebService::class.java)
        }
    }
}