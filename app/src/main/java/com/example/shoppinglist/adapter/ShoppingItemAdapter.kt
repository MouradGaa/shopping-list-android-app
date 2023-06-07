package com.example.shoppinglist.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.*
import com.example.shoppinglist.Data.ShoppingItem
import kotlinx.android.synthetic.main.dialog_shoping_item.view.*
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items : List<ShoppingItem>,
    private val viewModel: ShoppingViewModel,
    var context: Context
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item,parent,false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]

        holder.itemView.tvName.text= currentShoppingItem.name
        holder.itemView.tvPrice.text= "${currentShoppingItem.price}" + " HUF"
        holder.itemView.checkBox.isChecked = currentShoppingItem.status
        holder.itemView.deleteBtn.setOnClickListener{
            viewModel.delete(currentShoppingItem)
        }

        SetCategoriesIcon(currentShoppingItem,holder)

       holder.itemView.detailBtn.setOnClickListener{
           val name = currentShoppingItem.name
           val price = "${currentShoppingItem.price}"
           val description = currentShoppingItem.description
           val status = currentShoppingItem.status
           val category = currentShoppingItem.category

           var intentDetails = Intent("custom")
           intentDetails.setClass(context,DetailsActivity::class.java)

           intentDetails.putExtra("name",name)
           intentDetails.putExtra("price",price)
           intentDetails.putExtra("description",description)
           intentDetails.putExtra("status",status)
           intentDetails.putExtra("category",category)

           context.startActivity(intentDetails)
       }

       holder.itemView.checkBox.setOnClickListener{
           currentShoppingItem.status = holder.itemView.checkBox.isChecked
           viewModel.upsert(currentShoppingItem)
       }


    }

    fun SetCategoriesIcon(
        item : ShoppingItem,
        holder : ShoppingViewHolder
    ){
        if(item.category == "Food") holder.itemView.catLogo.setImageResource(R.drawable.food_icon)
        if(item.category == "Books") holder.itemView.catLogo.setImageResource(R.drawable.book_icon)
        if(item.category == "Electronics") holder.itemView.catLogo.setImageResource(R.drawable.electronics_icon)
        if(item.category == "Clothes") holder.itemView.catLogo.setImageResource(R.drawable.clothes_icon)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ShoppingViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}