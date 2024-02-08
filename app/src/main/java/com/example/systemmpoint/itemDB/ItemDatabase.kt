package com.example.systemmpoint.itemDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ItemTable::class), version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase(){
    abstract fun getItemsDao(): ItemDAO

    companion object{
        @Volatile
        private var INSTANCE: ItemDatabase? = null

        fun getDatabase(context: Context): ItemDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "item_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}