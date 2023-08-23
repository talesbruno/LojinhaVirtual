package com.example.lojinhavirtual.domain

import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend fun execute(): List<Category> {
        return repository.getCategories()
    }
}