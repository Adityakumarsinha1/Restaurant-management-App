package com.example.systemmpoint.orderDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface OrderDAO {
    @Query("SELECT * FROM orders")
    fun getAll(): LiveData<List<OrderTable>>

//    @Query("SELECT * FROM items WHERE id IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): LiveData<List<CustmerTable>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(order:OrderTable)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(order: OrderTable)

    @Delete
    suspend fun delete(order: OrderTable)
}