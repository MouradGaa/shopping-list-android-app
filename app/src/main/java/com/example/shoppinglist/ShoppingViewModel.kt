package com.example.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.Data.ShoppingItem
import com.example.shoppinglist.repositories.ShoppingListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingListRepository
):ViewModel() {
    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.delete(item)
    }

    fun getallItems() = repository.getAllitems()

    fun deleteAllItems() = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteAll()
    }
}