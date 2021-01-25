package com.gcr.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class UserDataBase:RoomDatabase() {

    abstract fun userDao():UserDao

    companion object{
        @Volatile
        private var INTANCE: UserDataBase? = null

        fun getDatabase(context: Context):UserDataBase{
            val tempIntance = INTANCE
            if(tempIntance != null){
                return tempIntance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "user_database"

                ).build()
                INTANCE = instance
                return instance
            }
        }
    }

}