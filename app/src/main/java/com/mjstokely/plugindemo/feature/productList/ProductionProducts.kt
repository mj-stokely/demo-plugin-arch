package com.mjstokely.plugindemo.feature.productList

import com.mjstokely.plugindemo.R
import com.mjstokely.plugindemo.feature.shared.Provider

object ProductionProducts: Provider<List<ProductViewModel>> {
    override fun provide(): List<ProductViewModel> =
        listOf(
                ProductViewModel(R.string.public_product_a),
                ProductViewModel(R.string.public_product_b)
        )
}