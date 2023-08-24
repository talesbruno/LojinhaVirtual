package com.example.lojinhavirtual.data

import android.content.Context
import com.example.lojinhavirtual.R
import com.example.lojinhavirtual.domain.Category
import com.example.lojinhavirtual.domain.Product
import com.example.lojinhavirtual.domain.ProductRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Scanner
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val context: Context) : ProductRepository {
    //    override suspend fun getCategories(): List<Category> {
//        val inputStream = context.resources.openRawResource(R.raw.fakedata)
//        val jsonString = Scanner(inputStream).useDelimiter("\\A").next()
//        val gson = Gson()
//
//        // Ajuste para o objeto raiz
//        val jsonObject = JSONObject(jsonString)
//        val categoriasArray = jsonObject.getJSONArray("categorias")
//
//        val categoriasType = object : TypeToken<List<Category>>() {}.type
//        return gson.fromJson(categoriasArray.toString(), categoriasType)
//    }
    override suspend fun getCategories(): List<Category> {
        val jsonString = readJSONFromAssets(context)
        return toCategories(jsonString)
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
    private fun toCategories(jsonAsString: String): List<Category> {
        val categories = mutableListOf<Category>()
        val jsonRoot = JSONObject(jsonAsString)
        val jsonCategories = jsonRoot.getJSONArray("categorias")
        for (i in 0 until jsonCategories.length()) {
            val jsonCategory = jsonCategories.getJSONObject(i)

            val title = jsonCategory.getString("nome")
            val icon = jsonCategory.getInt("icon")
            val jsonProducts = jsonCategory.getJSONArray("produtos")

            val products = mutableListOf<Product>()
            for (j in 0 until jsonProducts.length()) {
                val jsonProduct = jsonProducts.getJSONObject(j)
                val id = jsonProduct.getInt("id")
                val img = jsonProduct.getInt("img")
                val name = jsonProduct.getString("nome")
                val price = jsonProduct.getDouble("preco")
                val desc = jsonProduct.getInt("desconto")

                products.add(Product(id, name, price, desc, img))
            }
            categories.add(Category(title, icon, products))
        }
        return categories
    }

    private fun readJSONFromAssets(context: Context): String {
        return try {
            val file = context.resources.openRawResource(R.raw.fakedata)
            val bufferedReader = BufferedReader(InputStreamReader(file))
            val stringBuilder = StringBuilder()
            bufferedReader.useLines { lines ->
                lines.forEach {
                    stringBuilder.append(it)
                }
            }
            stringBuilder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

}


