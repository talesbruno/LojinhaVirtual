package com.example.lojinhavirtual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val txtOff: TextView = findViewById(R.id.product_txt_off)
        val txtTitle: TextView = findViewById(R.id.product_txt_title)
        val txtDesc: TextView = findViewById(R.id.product_txt_description)

        val toolbar: Toolbar = findViewById(R.id.product_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}