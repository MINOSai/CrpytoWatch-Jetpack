package com.minosai.archusers.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Quotes(
        @SerializedName("price") @Expose var usd: Usd = Usd()
)