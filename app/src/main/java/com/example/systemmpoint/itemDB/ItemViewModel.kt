package com.example.systemmpoint.itemDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application): AndroidViewModel(application) {

    val allItems: LiveData<List<ItemTable>>
    val repository: ItemRepository

    init {
        val dao=ItemDatabase.getDatabase(application).getItemsDao()
        repository=  ItemRepository(dao)
        allItems= repository.allItems
    }

    fun deleteItem(item : ItemTable) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(item)
    }
    fun updateItem(item: ItemTable) = viewModelScope.launch(Dispatchers.IO){
        repository.update(item)
    }
    fun insertItem(item: ItemTable) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(item )
    }

}