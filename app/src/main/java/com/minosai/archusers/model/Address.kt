package com.minosai.archusers.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by minos.ai on 10/05/18.
 */

data class Address(
        @SerializedName("street") @Expose val street: String = "",
        @SerializedName("suite") @Expose val suite: String = "",
        @SerializedName("city") @Expose val city: String = "",
        @SerializedName("zipcode") @Expose val zipcode: String = "",
        @SerializedName("geo") @Expose val geo: Geo = Geo()
)