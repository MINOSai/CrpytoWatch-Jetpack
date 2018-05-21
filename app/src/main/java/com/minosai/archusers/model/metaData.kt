package com.minosai.archusers.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class MetaData(
        @SerializedName("timestamp") @Expose var timestamp: Timestamp,
        @SerializedName("num_cryptocurrencies") @Expose var numCryptos: Int

)