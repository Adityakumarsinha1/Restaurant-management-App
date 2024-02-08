package com.example.systemmpoint.customerDB

import androidx.room.ColumnInfo
import  androidx.room.Entity
import  androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class CustmerTable(
    @ColumnInfo(name = "first_name") val name: String,
    @ColumnInfo(name = "time") val time :String,
    @ColumnInfo(name = "total") val total :String
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
