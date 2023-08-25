package com.example.lojinhavirtual.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lojinhavirtual.R
import com.example.lojinhavirtual.domain.Category
import com.example.lojinhavirtual.domain.OnProductClickListener

class MenuAdapter (
    private var categories: List<Category>,
    private val onCategoryClickListener: OnProductClickListener
    ) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

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
            val imageResourceMap = mapOf(
                "cama" to R.drawable.cama,
                "macaco" to R.drawable.macaco,
                "comedouros" to R.drawable.comedouros,
                "brinquedos" to R.drawable.brinquedos,
                "casinha" to R.drawable.casinha,
                "cama1" to R.drawable.cama1,
                "cama2" to R.drawable.cama2,
                "brinquedinho" to R.drawable.brinquedinho
            )
            val textTitle: TextView = itemView.findViewById(R.id.nomeCategoriaTextView)
            val icon: ImageView = itemView.findViewById(R.id.categoriaImageView)
            textTitle.text = category.name

            val iconResourceId = imageResourceMap[category.icon]
            if (iconResourceId != null) {
                icon.setImageResource(iconResourceId)
            } else {

            }
            itemView.setOnClickListener {
                onCategoryClickListener.onCategoryClick(category)
            }
        }
    }
}