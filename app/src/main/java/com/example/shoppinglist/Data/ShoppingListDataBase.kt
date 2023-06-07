package com.example.shoppinglist.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = arrayOf(ShoppingItem::class),version = 1)
abstract class ShoppingListDataBase: RoomDatabase () {

    abstract fun ShoppingDao(): ShoppingDAO

    companion object{
        @Volatile
        private var instance: ShoppingListDataBase? = null
        private var lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){ // check if the instance is null, if it is null
            instance?: createDatabase(context).also { instance = it}              // create a database.
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            ShoppingListDataBase::class.java,"ShoppingList.db").build()
    }
}