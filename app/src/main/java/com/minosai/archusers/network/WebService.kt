package com.minosai.archusers.network

import com.minosai.archusers.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by minos.ai on 10/05/18.
 */
interface WebService {
    @GET("users")
    fun fetchAllUsers(): Call<List<User>>

    @GET("users/{id}")
    fun fetchUserById(@Path("id") userId: String): Call<User>

    companion object {
        fun create(): WebService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(WebService::class.java)
        }
    }
}