package com.example.lojinhavirtual.domain

class GetCategoryUseCase (private val repository: ProductRepository) {
    suspend fun execute(): List<Category> {
        return repository.getCategories()
    }
}