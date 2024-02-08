package com.example.systemmpoint.itemDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemTable(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: String
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
