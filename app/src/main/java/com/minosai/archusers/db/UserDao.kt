package com.minosai.archusers.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.minosai.archusers.model.User

/**
 * Created by minos.ai on 10/05/18.
 */

@Dao
interface UserDao {
    @Insert
    fun insertUser(vararg user: User)

    @Query("SELECT COUNT(*) FROM user")
    fun getUsersCount(): Int

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: String): LiveData<User>
}