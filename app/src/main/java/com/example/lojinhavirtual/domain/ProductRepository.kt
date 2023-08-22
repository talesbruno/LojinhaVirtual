package com.example.lojinhavirtual.domain

interface ProductRepository {
    suspend fun getCategories(): List<Category>
    suspend fun getProductForCategory(category: String): List<Product>
}