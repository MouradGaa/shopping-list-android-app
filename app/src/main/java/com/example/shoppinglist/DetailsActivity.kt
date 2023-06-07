package com.example.shoppinglist


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppinglist.Data.MoneyRate
import com.example.shoppinglist.Data.ShoppingItem
import com.example.shoppinglist.adapter.ShoppingItemAdapter

import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.shopping_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder
import kotlin.math.roundToLong

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)



        val name = intent?.getStringExtra("name")
        val price = intent?.getStringExtra("price")
        val description = intent?.getStringExtra("description")
        val category = intent?.getStringExtra("category")

        if (price != null) {
            getMoneyRate(price.toFloat())
        }

        detailName.text = name
        detailPrice.text = price + " HUF"
        detailDescription.text = description
        detailCat.text = category

        if (category != null) {
            SetCategoriesIcon(category)
        }


    }
    fun SetCategoriesIcon(
        category : String
    ){
        if(category == "Food") detailImg.setImageResource(R.drawable.food_icon)
        if(category == "Books") detailImg.setImageResource(R.drawable.book_icon)
        if(category == "Electronics") detailImg.setImageResource(R.drawable.electronics_icon)
        if(category == "Clothes") detailImg.setImageResource(R.drawable.clothes_icon)
    }

    private fun getMoneyRate(price: Float){
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://api.frankfurter.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitMoney = retrofitBuilder.getMoney()

        retrofitMoney.enqueue(object : Callback<MoneyRate?> {
            override fun onResponse(call: Call<MoneyRate?>, response: Response<MoneyRate?>) {
                val responseBody= response.body()!!
                val HUFrate = responseBody.rates.HUF
                val EURrate = Math.round((responseBody.amount / HUFrate)*price*100.0)/100.0
                val USDrate = Math.round((responseBody.rates.USD / HUFrate)*price*100.0)/100.0
                val GBPPrice = Math.round((responseBody.rates.GBP / HUFrate)*price*100.0)/100.0

                detailPrice1.text = EURrate.toString() + " EUR"
                detailPrice2.text = USDrate.toString() + " USD"
                detailPrice3.text = GBPPrice.toString() + " GBP"
            }

            override fun onFailure(call: Call<MoneyRate?>, t: Throwable) {
            }
        })
    }

}