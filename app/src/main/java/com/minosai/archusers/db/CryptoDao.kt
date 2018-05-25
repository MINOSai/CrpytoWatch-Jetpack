package com.minosai.archusers.db

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.minosai.archusers.model.CurrencyData

/**
 * Created by minos.ai on 10/05/18.
 */

@Dao
interface CryptoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCryptos(currencyData: List<CurrencyData>)

    @Query("SELECT COUNT(*) FROM currencydata")
    fun getCryptoCount(): Int

    @Query("SELECT * FROM currencydata")
    fun getAllCryptos(): DataSource.Factory<Int, CurrencyData>

    @Query("SELECT * FROM currencydata WHERE id = :id")
    fun getCryptoById(id: Int): LiveData<CurrencyData>
}