package com.mjstokely.plugindemo.feature.productList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.mjstokely.plugindemo.feature.shared.ProductsProvider

class ProductListAdapter(private val layoutInflater: LayoutInflater)
    : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>(),
        ProductsProvider.Observer{

    private val products = mutableListOf<ProductViewModel>()

    override fun getItemCount(): Int = products.size

    override fun onProductsChanged(products: List<ProductViewModel>) {
        with(this.products) {
            clear()
            addAll(products)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val txtDisplay = layoutInflater.inflate(
                android.R.layout.simple_list_item_1,
                parent,
                false) as TextView

        return ProductViewHolder(txtDisplay)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, index: Int) {
        holder.txtDisplay.setText(products[index].displayName)
    }

    data class ProductViewHolder(val txtDisplay: TextView): RecyclerView.ViewHolder(txtDisplay)
}