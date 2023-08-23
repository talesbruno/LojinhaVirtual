package com.example.lojinhavirtual.domain

import javax.inject.Inject

class GetProductForCategoryUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend fun execute(category: String): List<Product> {
        return repository.getProductForCategory(category)
    }
}