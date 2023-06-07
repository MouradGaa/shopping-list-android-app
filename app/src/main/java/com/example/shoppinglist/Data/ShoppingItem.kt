package com.example.shoppinglist.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "price") var price: Float,
    @ColumnInfo(name = "status") var status: Boolean,
){
    @PrimaryKey(autoGenerate = true) var itemId : Long?=null
}

