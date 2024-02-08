package com.example.systemmpoint.customerDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CustomerDAO {
    @Query("SELECT * FROM customers")
    fun getAll(): LiveData<List<CustmerTable>>

    @Query("SELECT * FROM customers WHERE id IN ()")
    fun loadAllByIds(): LiveData<List<CustmerTable>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(users: CustmerTable)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(user: CustmerTable)

    @Delete
    suspend fun delete(user: CustmerTable)
}