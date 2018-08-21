package com.mjstokely.plugindemo.feature.productList

import com.mjstokely.plugindemo.R
import com.mjstokely.plugindemo.feature.shared.Provider

object ProductBProvider: Provider<List<ProductViewModel>> {
    override fun provide(): List<ProductViewModel> = listOf(ProductViewModel(R.string.secret_product_b))
}