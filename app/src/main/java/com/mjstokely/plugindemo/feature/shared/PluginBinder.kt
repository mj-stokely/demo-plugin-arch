package com.mjstokely.plugindemo.feature.shared

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.IBinder

class PluginBinder(private val context: Context,
                   private val packageManager: PackageManager) {

    fun bindPlugins() {

        // this Action should match what's in the Plugin Service manifest
        val discoverPlugins = Intent(ACTION_PLUGIN).setPackage(context.packageName)

        val resolveInfos = packageManager.queryIntentServices(
                discoverPlugins,
                0)

        for (info in resolveInfos) {
            val componentName = with(info.serviceInfo) {
                ComponentName(applicationInfo.packageName, name)
            }

            // create an Explicit Intent that we can use to bind
            // to this specific plugin
            val bindTarget = Intent().apply {
                component = componentName
            }
            context.bindService(
                    bindTarget,
                    createConnection(),
                    Context.BIND_AUTO_CREATE)
        }
    }

    private fun createConnection(): ServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, binder: IBinder) =
                try {

                    (binder as? Plugin)?.run {
                        // tell our plugin to init itself
                        init()
                    } ?: Unit

                } finally {
                    context.unbindService(this)
                }

        // ignored
        override fun onServiceDisconnected(name: ComponentName) = Unit
    }

    companion object {
        const val ACTION_PLUGIN = "com.mjstokely.plugindemo.PLUGIN"
    }
}