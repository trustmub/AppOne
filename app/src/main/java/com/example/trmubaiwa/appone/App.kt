package com.example.trmubaiwa.appone

import android.app.Application
import android.util.Log


class App : Application(), LifecycleDelegate {


    override fun onCreate() {
        super.onCreate()
        val lifeCycleHandler = AppLifecycleHandler(this)
        registerLifecycleHandler(lifeCycleHandler)
    }




    override fun onAppBackgrounded() {
        Log.d("State","App in Background")
    }

    override fun onAppForegrounded() {
        Log.d("State","App in Foreground")
    }

    override fun onAppDestroyed() {
        Log.d("State","App in Destroyed")
    }


    private fun registerLifecycleHandler(lifeCycleHandler: AppLifecycleHandler) {
        registerActivityLifecycleCallbacks(lifeCycleHandler)
        registerComponentCallbacks(lifeCycleHandler)

    }

}