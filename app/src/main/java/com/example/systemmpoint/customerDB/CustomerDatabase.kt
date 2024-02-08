package com.example.systemmpoint.customerDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(CustmerTable::class), version = 1, exportSchema = false)
abstract class CustomerDatabase : RoomDatabase(){
    abstract fun getCustomerDao():CustomerDAO

    companion object{
        @Volatile
        private var INSTANCE: CustomerDatabase? = null

        fun getDatabase(context: Context): CustomerDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CustomerDatabase::class.java,
                    "customer_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}