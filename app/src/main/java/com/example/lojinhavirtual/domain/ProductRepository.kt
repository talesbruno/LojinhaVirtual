package com.example.lojinhavirtual.domain

interface ProductRepository {
    suspend fun getCategories(): List<Category>
}