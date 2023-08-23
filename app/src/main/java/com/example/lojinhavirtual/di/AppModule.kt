package com.example.lojinhavirtual.di

import android.content.Context
import com.example.lojinhavirtual.data.ProductRepositoryImpl
import com.example.lojinhavirtual.domain.Category
import com.example.lojinhavirtual.domain.OnProductClickListener
import com.example.lojinhavirtual.domain.Product
import com.example.lojinhavirtual.presentation.adapters.CategoryAdapter
import com.example.lojinhavirtual.presentation.adapters.MenuAdapter
import com.example.lojinhavirtual.presentation.adapters.ProductAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideProductRepository(@ApplicationContext context: Context) =
        ProductRepositoryImpl(context)

    @Provides
    fun provideMenuAdapter(): MenuAdapter {
        return MenuAdapter(emptyList())
    }
    @Provides
    fun provideCategoryAdapter(
        categories: List<Category>,
        onProductClickListener: OnProductClickListener
    ): CategoryAdapter {
        return CategoryAdapter(categories, onProductClickListener)
    }

    @Provides
    fun provideProductAdapter(
        products: List<Product>,
        onProductClickListener: OnProductClickListener
    ): ProductAdapter {
        return ProductAdapter(products, onProductClickListener)
    }
}