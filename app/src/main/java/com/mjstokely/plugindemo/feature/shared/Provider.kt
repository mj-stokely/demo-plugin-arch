package com.mjstokely.plugindemo.feature.shared

interface Provider<out T> {
    fun provide(): T
}