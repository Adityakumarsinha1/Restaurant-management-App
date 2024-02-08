package com.example.systemmpoint.customerDB

import androidx.lifecycle.LiveData

class CustomerRepository(private val customerDAO: CustomerDAO) {
    val allCustomer: LiveData<List<CustmerTable>> = customerDAO.getAll()
    val customerbyid :LiveData<List<CustmerTable>> = customerDAO.loadAllByIds()

    suspend fun insert(customer : CustmerTable)
    {
        customerDAO.insertAll(customer)
    }
    suspend fun delete(customer : CustmerTable)
    {
        customerDAO.delete(customer)
    }
    suspend fun update(customer : CustmerTable)
    {
        customerDAO.update(customer)
    }

}