package com.example.trmubaiwa.appone.Activities

import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.Build
import android.app.DialogFragment
import android.content.Intent
import com.example.trmubaiwa.appone.R
import com.example.trmubaiwa.appone.fingerprintLogin.FingerprintLoginFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set present a dialog box for fingerprint if there is hardware and permission are in place
        if (isFingerprintHardwareAvailable() && isFingerprintEnrolled()) {
            //            openFingerPrintDialog()
            println("all hardware requirements are meet")

        } else {
            println("cannot use the finger print")
//            openFingerPrintDialo()openFingerPrintDialo
        }

        shopBtn.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isFingerprintEnrolled(): Boolean {
        var state = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val fingerprintManager = this.getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager
            if (fingerprintManager.hasEnrolledFingerprints()) {
                state = true
            }
        }
        return state
    }

    //    @RequiresApi(Build.VERSION_CODES.M)
    private fun isFingerprintHardwareAvailable(): Boolean {
        var state = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val fingerprintManager = this.getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager

            if (!fingerprintManager.isHardwareDetected) {
                state = false
            }
        }
        return state
    }

    private fun showDialog(fragment: DialogFragment) {
        val transaction = fragmentManager.beginTransaction()

        //Remove the previously dialog
//        val previousDialog = fragmentManager.findFragmentByTag("dialog")
//        if (previousDialog != null) transaction.remove(previousDialog)
        transaction.addToBackStack(null)

        // Show a dialog with the fragment and close the transaction
        fragment.show(fragmentManager, "dialog")
    }

    fun openFingerPrintDialog(view: View) {
        val fragment = FingerprintLoginFragment()
        fragment.show(fragmentManager, "someframent")


//        val builder = AlertDialog.Builder(this)
//        val dialogView = layoutInflater.inflate(R.layout.fingerprint_login_main, null)
//
//        builder.setView(dialogView)
//                .setNegativeButton("USE PASSWORD") { _, _ ->
//                    // do something in this dialog
//                    println("dismiss the dialog box")
//                }
//                .setPositiveButton("CANCEL") { _, _ ->
//                    //dismiss the dialog box
//                    println("Envoke an enable password intent")
//                }
//                .show()

//        dialog.show(fragmentManager, "enable fingerprint")
//        dialog.show(fragmentManager,"we")
//        dialog
    }

}
