package com.example.lojinhavirtual.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lojinhavirtual.R
import com.example.lojinhavirtual.domain.Category
import javax.inject.Inject

class MenuAdapter (private var categories: List<Category>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    fun updateMenu(newCategories: List<Category>) {
        categories = newCategories
        notifyDataSetChanged()
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category){
            val textTitle: TextView = itemView.findViewById(R.id.nomeCategoriaTextView)
            val icon: ImageView = itemView.findViewById(R.id.categoriaImageView)
            textTitle.text = category.name
            icon.setImageResource(category.icon)
        }
    }
}