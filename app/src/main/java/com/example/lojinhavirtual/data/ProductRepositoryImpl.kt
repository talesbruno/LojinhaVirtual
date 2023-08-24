package com.example.lojinhavirtual.data

import android.content.Context
import android.content.res.Resources
import com.example.lojinhavirtual.R
import com.example.lojinhavirtual.domain.Category
import com.example.lojinhavirtual.domain.Product
import com.example.lojinhavirtual.domain.ProductRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Scanner
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val context: Context) : ProductRepository {
    override suspend fun getCategories(): List<Category> {
        val inputStream = context.resources.openRawResource(R.raw.fakedata)
        val jsonString = Scanner(inputStream).useDelimiter("\\A").next()
        val gson = Gson()

        // Ajuste para o objeto raiz
        val jsonObject = JSONObject(jsonString)
        val categoriasArray = jsonObject.getJSONArray("categorias")

        val categoriasType = object : TypeToken<List<Category>>() {}.type
        return gson.fromJson(categoriasArray.toString(), categoriasType)
    }

    override suspend fun getProductForCategory(category: String): List<Product> {
        val inputStream = context.resources.openRawResource(R.raw.fakedata)
        val jsonString = Scanner(inputStream).useDelimiter("\\A").next()
        val gson = Gson()

        // Ajuste para o objeto raiz
        val jsonObject = JSONObject(jsonString)
        val categoriasArray = jsonObject.getJSONArray("categorias")

        val categoriasType = object : TypeToken<List<Category>>() {}.type
        val categorias: List<Category> = gson.fromJson(categoriasArray.toString(), categoriasType)

        val produtosPorCategoria = mutableListOf<Product>()

        for (cat in categorias) {
            if (cat.name == category) {
                produtosPorCategoria.addAll(cat.products)
                break
            }
        }

        return produtosPorCategoria
    }
    //    override suspend fun getCategories(): List<Category> = withContext(Dispatchers.IO) {
//        val resources: Resources = context.resources
//        val inputStream = resources.openRawResource(R.raw.fakedata)
//        val reader = BufferedReader(InputStreamReader(inputStream))
//        val json = reader.readText()
//
//        val gson = Gson()
//        val categories: List<Category> = gson.fromJson(json, Array<Category>::class.java).toList()
//
//        categories
//    }
}


