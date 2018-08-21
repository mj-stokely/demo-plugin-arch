package com.mjstokely.plugindemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.mjstokely.plugindemo.feature.shared.Plugin
import com.mjstokely.plugindemo.feature.shared.ProductsProvider
import com.mjstokely.plugindemo.productList.ProductAProvider

class ProductAPluginService: Service() {

    private val binder = LocalBinder()

    override fun onBind(intent: Intent?): IBinder = binder

    class LocalBinder : Binder(), Plugin {
        override fun init() {
            ProductsProvider.addProvider(ProductAProvider)
        }
    }
}