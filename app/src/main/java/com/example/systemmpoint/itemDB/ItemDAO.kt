package com.example.systemmpoint.itemDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface ItemDAO {
    @Query("SELECT * FROM items")
    fun getAll(): LiveData<List<ItemTable>>

//    @Query("SELECT * FROM items WHERE id IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): LiveData<List<CustmerTable>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(items: ItemTable)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(items: ItemTable)

    @Delete
    suspend fun delete(items: ItemTable)
}