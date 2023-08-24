package com.example.lojinhavirtual.domain

interface OnProductClickListener {
    fun onProductClick(product: Product)

    fun onCategoryClick(category: Category)
}