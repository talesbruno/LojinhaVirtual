package com.example.lojinhavirtual.di

import android.content.Context
import com.example.lojinhavirtual.data.ProductRepositoryImpl
import com.example.lojinhavirtual.domain.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideProductRepository(@ApplicationContext context: Context) : ProductRepository =
        ProductRepositoryImpl(context)
}