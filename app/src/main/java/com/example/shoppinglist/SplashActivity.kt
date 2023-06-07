package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler



class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent = Intent(this, ShoppingListActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // delaying for 3 seconds
    }
}