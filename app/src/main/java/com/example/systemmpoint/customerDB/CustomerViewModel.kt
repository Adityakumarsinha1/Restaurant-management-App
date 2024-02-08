package com.example.systemmpoint.customerDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CustomerViewModel(application: Application): AndroidViewModel(application) {

    val allCustomer: LiveData<List<CustmerTable>>
    val customerById: LiveData<List<CustmerTable>>
    val repository: CustomerRepository

    init {
        val dao = CustomerDatabase.getDatabase(application).getCustomerDao()
        repository=  CustomerRepository(dao)
        allCustomer= repository.allCustomer
        customerById = repository.customerbyid
    }

    fun deleteCustomer(customer : CustmerTable) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(customer)
    }
    fun updateCustomer(customer : CustmerTable) = viewModelScope.launch(Dispatchers.IO){
        repository.update(customer)
    }
    fun insertCustomer(customer : CustmerTable) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(customer)
    }

}