package com.example.lojinhavirtual.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lojinhavirtual.R
import com.example.lojinhavirtual.domain.OnProductClickListener
import com.example.lojinhavirtual.domain.Product
import javax.inject.Inject

class ProductAdapter(
    private val products: List<Product>,
    private val onProductClickListener: OnProductClickListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(product: Product) {
            val desc: TextView = itemView.findViewById(R.id.product_txt_off)
            val imageCover: ImageView = itemView.findViewById(R.id.img_cover)
            val title: TextView = itemView.findViewById(R.id.product_name)
            val price: TextView = itemView.findViewById(R.id.tv_old_price)

            if (product.desc.toString().isNotBlank()) {
                desc.text = "${product.desc}% OFF"
                desc.visibility = View.VISIBLE
            } else {
                desc.visibility = View.GONE
            }
            val resources = itemView.context.resources
            val iconResourceId = resources.getIdentifier(product.coverUrl, "drawable", itemView.context.packageName)

            if (iconResourceId != 0) {
                imageCover.setImageResource(iconResourceId)
            } else {
            }
            title.text = product.name
            price.text = product.price.toString()

            imageCover.setOnClickListener {
                onProductClickListener.onProductClick(product)
            }
        }
    }
}