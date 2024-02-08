package com.example.systemmpoint.itemDB

import androidx.lifecycle.LiveData

class ItemRepository(private val itemDAO: ItemDAO) {
    val allItems: LiveData<List<ItemTable>> = itemDAO.getAll()
//    val customerbyid :LiveData<List<CustmerTable>> = customerDAO.getAll()

    suspend fun insert(item: ItemTable)
    {
        itemDAO.insertAll(item)
    }
    suspend fun delete(item: ItemTable)
    {
        itemDAO.delete(item)
    }
    suspend fun update(item: ItemTable)
    {
        itemDAO.update(item)
    }

}