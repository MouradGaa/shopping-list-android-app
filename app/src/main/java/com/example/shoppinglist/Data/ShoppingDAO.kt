package com.example.shoppinglist.Data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ShoppingDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // insert and update
    fun upsert(item:ShoppingItem)

    @Delete
    fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllItems(): LiveData<List<ShoppingItem>>

    @Query("DELETE FROM shopping_items")
     fun deleteAll()
}