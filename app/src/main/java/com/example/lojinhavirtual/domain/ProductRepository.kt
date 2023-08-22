package com.example.lojinhavirtual.domain

interface ProductRepository {
    fun getCategories(): List<Category>
}