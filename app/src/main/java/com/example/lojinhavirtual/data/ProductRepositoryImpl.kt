package com.example.lojinhavirtual.data

import android.content.Context
import com.example.lojinhavirtual.R
import com.example.lojinhavirtual.domain.Category
import com.example.lojinhavirtual.domain.Product
import com.example.lojinhavirtual.domain.ProductRepository
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val context: Context) : ProductRepository {
    override suspend fun getCategories(): List<Category> {
        val jsonString = readJSONFromAssets(context)
        return toCategories(jsonString)
    }

    private fun toCategories(jsonAsString: String): List<Category> {
        val categories = mutableListOf<Category>()
        val jsonRoot = JSONObject(jsonAsString)
        val jsonCategories = jsonRoot.getJSONArray("categorias")
        for (i in 0 until jsonCategories.length()) {
            val jsonCategory = jsonCategories.getJSONObject(i)

            val title = jsonCategory.getString("nome")
            val icon = jsonCategory.getString("icon")
            val jsonProducts = jsonCategory.getJSONArray("produtos")

            val products = mutableListOf<Product>()
            for (j in 0 until jsonProducts.length()) {
                val jsonProduct = jsonProducts.getJSONObject(j)
                val id = jsonProduct.getInt("id")
                val img = jsonProduct.getString("img")
                val name = jsonProduct.getString("nome")
                val price = jsonProduct.getDouble("preco")
                val desc = jsonProduct.optString("desconto")

                val discount = if (desc.isEmpty()) null else desc.toIntOrNull()
                products.add(Product(id, name, price, discount, img))
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


