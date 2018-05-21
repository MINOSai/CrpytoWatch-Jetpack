package com.minosai.archusers.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class CurrencyData(
        @PrimaryKey
        @SerializedName("id") @Expose var id: Int = 0,
        @SerializedName("name") @Expose var name: String = "",
        @SerializedName("symbol") @Expose var symbol: String = "",
        @SerializedName("rank") @Expose var rank: Int = 0,
        @SerializedName("quotes") @Expose var quotes: Quotes = Quotes()
)