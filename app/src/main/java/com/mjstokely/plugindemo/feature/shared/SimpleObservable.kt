package com.mjstokely.plugindemo.feature.shared

import android.database.Observable
import java.util.*

class SimpleObservable<T> : Observable<T>() {
    val observers: List<T>
        get() = Collections.unmodifiableList(mObservers)
}