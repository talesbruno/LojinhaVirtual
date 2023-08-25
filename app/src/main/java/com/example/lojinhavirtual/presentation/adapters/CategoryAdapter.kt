package com.example.lojinhavirtual.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lojinhavirtual.R
import com.example.lojinhavirtual.domain.Category
import com.example.lojinhavirtual.domain.OnProductClickListener

class CategoryAdapter(
    private var categories: List<Category>,
    private val onProductClickListener: OnProductClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var filteredCategories: List<Category> = categories

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredCategories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = filteredCategories[position]
        holder.bind(category)
    }

    fun updateCategories(newCategories: List<Category>) {
        categories = newCategories
        applyFilter("")
        notifyDataSetChanged()
    }

    fun applyFilter(query: String) {
        filteredCategories = if (query.isBlank()) {
            categories
        } else {
            categories.filter { category ->
                category.products.any { product ->
                    product.name.contains(query, ignoreCase = true)
                }
            }
        }
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category){
            val textTitle: TextView = itemView.findViewById(R.id.txt_cat_title)
            textTitle.text = category.name

            val rvCategory: RecyclerView = itemView.findViewById(R.id.rv_category)
            rvCategory.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            rvCategory.adapter = ProductAdapter(category.products, onProductClickListener)
        }
    }
}
