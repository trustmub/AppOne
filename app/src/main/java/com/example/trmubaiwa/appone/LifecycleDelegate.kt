package com.example.trmubaiwa.appone

/**
 * Created by trmubaiwa on 2018/01/31.
 */
interface LifecycleDelegate {

        fun onAppBackgrounded()
        fun onAppForegrounded()
        fun onAppDestroyed()

}