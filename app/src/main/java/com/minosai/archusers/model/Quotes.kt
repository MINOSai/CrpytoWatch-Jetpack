package com.minosai.archusers.model

import android.arch.persistence.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Quotes(
        @SerializedName("price") @Expose @Embedded var usd: Usd = Usd()
)