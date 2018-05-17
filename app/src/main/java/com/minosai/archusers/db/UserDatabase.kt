package com.minosai.archusers.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.minosai.archusers.model.User

/**
 * Created by minos.ai on 10/05/18.
 */

@Database(entities = [(User::class)], version = 1)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "userdatabase"
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase? {
            INSTANCE?.let {
                INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}