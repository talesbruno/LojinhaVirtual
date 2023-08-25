package com.example.lojinhavirtual.domain

data class Category(
    val name: String,
    val icon: String,
    val products: List<Product>
)