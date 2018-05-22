package com.minosai.archusers.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by minos.ai on 10/05/18.
 */

data class User(
        @PrimaryKey
        @SerializedName("id") @Expose val id: String = "",
        @SerializedName("username") @Expose val userName: String = "",
        @SerializedName("email") @Expose val email: String = "",
        @SerializedName("address") @Expose val address: Address = Address(),
        @SerializedName("phone") @Expose val phone: String = "",
        @SerializedName("website") @Expose val website: String = "",
        @SerializedName("company") @Expose val company: Company = Company()
)