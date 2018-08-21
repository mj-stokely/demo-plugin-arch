package com.mjstokely.plugindemo.feature

import android.app.Application
import com.mjstokely.plugindemo.feature.productList.ProductionProducts
import com.mjstokely.plugindemo.feature.shared.PluginBinder
import com.mjstokely.plugindemo.feature.shared.ProductsProvider

class PluginDemoApp: Application() {

    override fun onCreate() {
        super.onCreate()

        ProductsProvider.addProvider(ProductionProducts)
        initPlugins()
    }

    private fun initPlugins() {
        PluginBinder(this, packageManager).bindPlugins()
    }
}