package com.mjstokely.plugindemo.feature.shared

import com.mjstokely.plugindemo.feature.productList.ProductViewModel
import java.util.concurrent.CopyOnWriteArrayList

object ProductsProvider : Provider<List<ProductViewModel>> {

    // expected for providers to be added infrequently (likely at start-up only really),
    // so CopyOnWriteArrayList is a good thread-safe collection choice
    private val providers = CopyOnWriteArrayList<Provider<List<ProductViewModel>>>()

    // total list of providers will asynchronously update as Plugins are discovered. need to be able
    // to observe changes
    private val observable = SimpleObservable<Observer>()

    // pass back aggregate list of view models from all plugins
    override fun provide(): List<ProductViewModel> =
            providers.map { it.provide() }
                    .fold(mutableListOf()) { current, next ->
                        current.apply { addAll(next) }
                    }

    fun addProvider(provider: Provider<List<ProductViewModel>>) =
            providers.add(provider)
                    .also {
                        // added a new provider, notify observers of the new product list
                        notifyObservers()
                    }

    fun registerObserver(observer: Observer) =
            observable.registerObserver(observer)
                    .also {
                        // provide current state when observer is registered
                        observer.onProductsChanged(provide())
                    }

    fun unregisterObserver(observer: Observer) = observable.unregisterObserver(observer)

    private fun notifyObservers() {
        val products = provide()
        val observers = observable.observers
        observers.forEach { observer ->
            observer.onProductsChanged(products)
        }
    }

    interface Observer {
        fun onProductsChanged(products: List<ProductViewModel>)
    }
}