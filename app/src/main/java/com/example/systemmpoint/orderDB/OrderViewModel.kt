package com.example.systemmpoint.orderDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel(application: Application): AndroidViewModel(application) {

    val allOrders: LiveData<List<OrderTable>>
    val repository: OrderRepository

    init {
        val dao= OrderDatabase.getDatabase(application).getItemsDao()
        repository=  OrderRepository(dao)
        allOrders= repository.allItems
    }

    fun deleteItem(item :OrderTable) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(item)
    }
    fun updateItem(item: OrderTable) = viewModelScope.launch(Dispatchers.IO){
        repository.update(item)
    }
    fun insertItem(item: OrderTable) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(item )
    }

}