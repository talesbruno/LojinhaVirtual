package com.example.lojinhavirtual.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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
            val card: ConstraintLayout = itemView.findViewById(R.id.card_product)
            val desc: TextView = itemView.findViewById(R.id.product_txt_off)
            val imageCover: ImageView = itemView.findViewById(R.id.img_cover)
            val title: TextView = itemView.findViewById(R.id.product_name)
            val price: TextView = itemView.findViewById(R.id.tv_old_price)
            val newPrice: TextView = itemView.findViewById(R.id.tv_current_price)
            val de: TextView = itemView.findViewById(R.id.textView2)

            if (product.desc != null) {
                val discountedPrice = product.price * (1 - (product.desc / 100))
                desc.text = "${product.desc}% OFF"
                price.text = "R$ ${product.price}"
                newPrice.text = "R$ ${String.format("%.2f", discountedPrice)}"
                desc.visibility = View.VISIBLE
                price.visibility = View.VISIBLE
                de.visibility = View.VISIBLE
            } else {
                newPrice.text = "R$ ${product.price}"
                desc.visibility = View.GONE
                price.visibility = View.GONE
                de.visibility = View.GONE
            }
            val resources = itemView.context.resources
            val iconResourceId =
                resources.getIdentifier(product.coverUrl, "drawable", itemView.context.packageName)

            if (iconResourceId != 0) {
                imageCover.setImageResource(iconResourceId)
            } else {
            }
            title.text = product.name

            card.setOnClickListener {
                onProductClickListener.onProductClick(product)
            }
        }
    }
}