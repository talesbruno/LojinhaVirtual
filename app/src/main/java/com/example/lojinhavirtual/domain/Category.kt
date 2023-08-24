package com.example.lojinhavirtual.domain

import androidx.annotation.DrawableRes

data class Category(
    val name: String,
    val icon: String,
    val products: List<Product>
)