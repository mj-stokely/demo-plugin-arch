package com.mjstokely.plugindemo.productList

import com.mjstokely.plugindemo.R
import com.mjstokely.plugindemo.feature.productList.ProductViewModel
import com.mjstokely.plugindemo.feature.shared.Provider

object ProductAProvider: Provider<List<ProductViewModel>> {
    override fun provide(): List<ProductViewModel> = listOf(ProductViewModel(R.string.secret_product_a))
}