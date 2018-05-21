package com.minosai.archusers.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Usd(
        @SerializedName("price") @Expose var price: Double = 0.0,
        @SerializedName("percent_change_1h") @Expose var change1Hour: Double = 0.0,
        @SerializedName("percent_change_24h") @Expose var change24Hours: Double = 0.0,
        @SerializedName("percent_change_7d") @Expose var change7Days: Double = 0.0
)