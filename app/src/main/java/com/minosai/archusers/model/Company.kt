package com.minosai.archusers.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by minos.ai on 10/05/18.
 */

data class Company(
        @SerializedName("name") @Expose val name: String = "",
        @SerializedName("catchPhrase") @Expose val catchPhrase: String = "",
        @SerializedName("bs") @Expose val bs: String = ""
)