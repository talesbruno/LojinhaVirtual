package com.example.lojinhavirtual.data

import android.content.Context
import com.example.lojinhavirtual.R
import com.example.lojinhavirtual.domain.Category
import com.example.lojinhavirtual.domain.Product
import com.example.lojinhavirtual.domain.ProductRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Scanner

class ProductRepositoryImpl (private val context: Context) : ProductRepository {
    override suspend fun getCategories(): List<Category> {
        val inputStream = context.resources.openRawResource(R.raw.fakedata)
        val jsonString = Scanner(inputStream).useDelimiter("\\A").next()
        val gson = Gson()
        val categoriasType = object : TypeToken<List<Category>>() {}.type
        return gson.fromJson(jsonString, categoriasType)
    }

    override suspend fun getProductForCategory(category: String): List<Product> {
        val inputStream = context.resources.openRawResource(R.raw.fakedata)
        val jsonString = Scanner(inputStream).useDelimiter("\\A").next()
        val gson = Gson()
        val categoriasType = object : TypeToken<List<Category>>() {}.type
        val categorias: List<Category> = gson.fromJson(jsonString, categoriasType)

        val produtosPorCategoria = mutableListOf<Product>()

        for (cat in categorias) {
            if (cat.name == category) {
                produtosPorCategoria.addAll(cat.products)
                break
            }
        }

        return produtosPorCategoria
    }
}