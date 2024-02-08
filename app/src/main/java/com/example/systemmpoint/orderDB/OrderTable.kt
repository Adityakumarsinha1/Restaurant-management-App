package com.example.systemmpoint.orderDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderTable(
    @ColumnInfo(name = "item_id") val iid: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "customer_id") val cid: String,
    @ColumnInfo(name = "sub_total") val stotal: Int
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}