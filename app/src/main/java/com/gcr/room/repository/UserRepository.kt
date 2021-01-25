package com.gcr.room.repository

import androidx.lifecycle.LiveData
import com.gcr.room.database.User
import com.gcr.room.database.UserDao

class UserRepository (private val userDao: UserDao){

    val readAllData: LiveData<List<User>> = userDao.getAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser(){
        userDao.deleteAllUsers()
    }

}