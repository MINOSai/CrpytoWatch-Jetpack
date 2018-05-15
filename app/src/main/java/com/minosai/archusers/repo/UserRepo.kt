package com.minosai.archusers.repo

import android.content.Context
import com.minosai.archusers.db.UserDatabase
import com.minosai.archusers.network.WebService

/**
 * Created by minos.ai on 10/05/18.
 */

class UserRepo(context: Context) {

    private var webService: WebService = WebService.create()

    fun getUsers() {

    }
}