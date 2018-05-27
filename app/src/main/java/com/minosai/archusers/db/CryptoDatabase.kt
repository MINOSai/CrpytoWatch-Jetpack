package com.minosai.archusers.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.minosai.archusers.model.CurrencyData
import com.minosai.archusers.model.User

/**
 * Created by minos.ai on 10/05/18.
 */

@Database(entities = [(CurrencyData::class)], version = 1)
abstract class CryptoDatabase: RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao
    lateinit var INSTANCE: CryptoDatabase

}