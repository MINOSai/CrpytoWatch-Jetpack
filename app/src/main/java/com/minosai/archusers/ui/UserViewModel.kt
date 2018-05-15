package com.minosai.archusers.ui

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.minosai.archusers.db.UserDatabase
import com.minosai.archusers.model.User

/**
 * Created by minos.ai on 16/05/18.
 */
class UserViewModel(application : Application) : ViewModel() {

    val dao = UserDatabase.getInstance(application)?.userDao()
    var userList: LiveData<List<User>>? = null

    init {
        if(dao?.getUsersCount() == 0) {
            fetchUsers()
        }
        userList = dao?.getAllUsers()
    }

    private fun fetchUsers() {

    }

    fun getUserById(id: String) = dao?.getUserById(id)

}