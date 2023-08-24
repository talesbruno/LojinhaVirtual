package com.example.lojinhavirtual.domain

import androidx.annotation.DrawableRes

data class Category(
    val name: String,
    @DrawableRes val icon: Int,
    val products: List<Product>
)