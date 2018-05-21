package com.minosai.archusers.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponse(
        @SerializedName("data") @Expose val data: List<CurrencyData>,
        @SerializedName("metadata") @Expose val metaData: MetaData
)