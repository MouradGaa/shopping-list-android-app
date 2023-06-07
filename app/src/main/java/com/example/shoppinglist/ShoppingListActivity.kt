package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.Data.ShoppingItem
import com.example.shoppinglist.Data.ShoppingListDataBase
import com.example.shoppinglist.adapter.ShoppingItemAdapter
import com.example.shoppinglist.repositories.ShoppingListRepository
import kotlinx.android.synthetic.main.activity_shopping_list.*

class ShoppingListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)

        val database = ShoppingListDataBase(this)
        val repository = ShoppingListRepository(database)
        val factory= ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(),viewModel,this)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter=adapter

        viewModel.getallItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener{
            ShoppingItemDialog(this,object : AddDialogListener{
               override  fun onAddButtomClicked(item: ShoppingItem) {
                   viewModel.upsert(item)
               }
            }).show()
        }

        deleteAll_btn.setOnClickListener{
            viewModel.deleteAllItems()
        }

    }

}