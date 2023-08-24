package com.example.lojinhavirtual.domain

import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend fun execute(): List<Category> {
        return repository.getCategories()
    }

    suspend fun searchProductsByName(query: String): List<Product> {
        val allCategories = execute()
        val allProducts = allCategories.flatMap { it.products }
        return allProducts.filter { it.name.contains(query, ignoreCase = true) }
    }
}