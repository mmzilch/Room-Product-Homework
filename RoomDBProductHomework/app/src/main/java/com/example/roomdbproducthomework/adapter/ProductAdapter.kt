package com.example.roomdbproducthomework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbproducthomework.R
import com.example.roomdbproducthomework.entity.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var products = emptyList<Product>()

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindWord(product: Product) {
            itemView.tvProductId.text = product.productId.toString()
            itemView.tvProductName.text=product.productName
            itemView.tvProductPrice.text=product.price.toString()
            itemView.tvProductQuantity.text=product.quantity.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindWord(products[position])
    }

    fun setProduct(products: List<Product>){
        this.products=products
        notifyDataSetChanged()
    }
}