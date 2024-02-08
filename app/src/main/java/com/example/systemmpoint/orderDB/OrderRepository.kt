package com.example.systemmpoint.orderDB

import androidx.lifecycle.LiveData

class OrderRepository(private val orderDAO: OrderDAO) {
    val allItems: LiveData<List<OrderTable>> =orderDAO.getAll()
//    val customerbyid :LiveData<List<CustmerTable>> = customerDAO.getAll()

    suspend fun insert(order: OrderTable)
    {
        orderDAO.insertAll(order)
    }
    suspend fun delete(order: OrderTable)
    {
        orderDAO.delete(order)
    }
    suspend fun update(order: OrderTable)
    {
        orderDAO.update(order)
    }

}