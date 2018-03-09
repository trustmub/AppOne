package com.example.trmubaiwa.appone.fingerprintLogin

import android.hardware.fingerprint.FingerprintManager
import android.widget.Toast
import com.example.trmubaiwa.appone.Activities.MainActivity


class AuthenticationHandler : FingerprintManager.AuthenticationCallback() {

    lateinit var mainActivity: MainActivity

    fun AuthenticationHandler(mainActivity: MainActivity) {
        this.mainActivity = mainActivity
    }


    override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
        super.onAuthenticationError(errorCode, errString)
        Toast.makeText(mainActivity, "Auth Error" + errString, Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult?) {
        super.onAuthenticationSucceeded(result)
        Toast.makeText(mainActivity, "Auth Success" + result, Toast.LENGTH_LONG).show()
        println("authentication worked")
    }

    override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
        super.onAuthenticationHelp(helpCode, helpString)
        Toast.makeText(mainActivity, "Auth Help" + helpString, Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationFailed() {
        super.onAuthenticationFailed()
        Toast.makeText(mainActivity, "Auth Failed", Toast.LENGTH_LONG).show()
        println("authentication failed")

    }


}