package com.example.lojinhavirtual.domain

class GetProductForCategoryUseCase (private val repository: ProductRepository) {
    suspend fun execute(category: String): List<Product> {
        return repository.getProductForCategory(category)
    }
}