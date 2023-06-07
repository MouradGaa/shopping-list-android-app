package com.example.shoppinglist


import com.example.shoppinglist.Data.MoneyRate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/latest")
    fun getMoney() : Call<MoneyRate>
}