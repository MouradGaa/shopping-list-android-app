package com.example.shoppinglist.repositories

import com.example.shoppinglist.Data.ShoppingItem
import com.example.shoppinglist.Data.ShoppingListDataBase

class ShoppingListRepository(
    private val db:ShoppingListDataBase
) {
    fun upsert(item:ShoppingItem) = db.ShoppingDao().upsert(item)

    fun delete(item: ShoppingItem) = db.ShoppingDao().delete(item)

    fun deleteAll() = db.ShoppingDao().deleteAll()

    fun getAllitems() = db.ShoppingDao().getAllItems()


}