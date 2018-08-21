package com.mjstokely.plugindemo.feature.productList

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.mjstokely.plugindemo.R
import com.mjstokely.plugindemo.feature.shared.ProductsProvider

class ProductsActivity : AppCompatActivity() {

    private lateinit var adapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_products)

        adapter = ProductListAdapter(LayoutInflater.from(this))

        val productList = findViewById<RecyclerView>(R.id.rv_products)
        productList.adapter = adapter
        productList.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        ProductsProvider.registerObserver(adapter)
    }

    override fun onStop() {
        super.onStop()
        ProductsProvider.unregisterObserver(adapter)
    }
}