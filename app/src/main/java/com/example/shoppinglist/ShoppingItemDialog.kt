package com.example.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.Data.ShoppingItem
import kotlinx.android.synthetic.main.dialog_shoping_item.*
import kotlinx.android.synthetic.main.shopping_item.*

class ShoppingItemDialog(context: Context,var addDialogListener: AddDialogListener): AppCompatDialog(context) {

    lateinit var categoryOption : Spinner
    lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_shoping_item)

        categoryOption = findViewById(R.id.spinner)!!

        val options = arrayOf("Food","Books","Electronics","Clothes")
        categoryOption.adapter = ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,options)

        categoryOption.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                category = options.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        tvAdd.setOnClickListener{
            val name = name_et.text.toString()
            val description = description_et.text.toString()
            val price = price_et.text.toString()
            val status = status_et.isChecked

            if(name.isEmpty() || price.isEmpty()){
                Toast.makeText(context,"Please enter missing information",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(category,name,description,price.toFloat(),status)
            addDialogListener.onAddButtomClicked(item)
            dismiss()
        }

        tvCancel.setOnClickListener{
            cancel()
        }
    }

}