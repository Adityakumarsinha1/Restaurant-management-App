package com.example.systemmpoint.orderDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(OrderTable::class), version = 1, exportSchema = false)
abstract class OrderDatabase : RoomDatabase(){
    abstract fun getItemsDao(): OrderDAO

    companion object{
        @Volatile
        private var INSTANCE: OrderDatabase? = null

        fun getDatabase(context: Context): OrderDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OrderDatabase::class.java,
                    "item_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}