package com.example.trmubaiwa.appone.fingerprintLogin


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.app.DialogFragment
import android.app.KeyguardManager
import android.content.Context
import android.content.Context.FINGERPRINT_SERVICE

import android.content.Context.KEYGUARD_SERVICE
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.CancellationSignal
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.trmubaiwa.appone.R
import kotlinx.android.synthetic.main.fingerprint_login_main.view.*
import kotlinx.android.synthetic.main.fragment_fingerprint_login.*
import kotlinx.android.synthetic.main.fragment_fingerprint_login.view.*
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.util.jar.Manifest
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey


//@Suppress("CAST_NEVER_SUCCEEDS")
/**
 * A simple [Fragment] subclass.
 */
class FingerprintLoginFragment : DialogFragment() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val keyguardManager = getSystemService<KeyguardManager>(KeyguardManager::class.java)
//        val fingerprintManager = getSystemService<FingerprintManager>(FingerprintManager::class.java)


        val keyguardManager = activity.getSystemService(KEYGUARD_SERVICE) as KeyguardManager
        val fingerprintManager = activity.getSystemService(FINGERPRINT_SERVICE) as FingerprintManager
        Log.d("Finger", "in oncreate view of the fragment")

        if (hasHardwareAndEnrolled(fingerprintManager, keyguardManager)) {
            Log.d("Finger", "Hardware is in place")


            val keystore: KeyStore


            try {
                keystore = KeyStore.getInstance("AndroidKeyStore")
            } catch (e: KeyStoreException) {
                throw RuntimeException("Failed to get an instance of KeyStore", e)
            }

            val keyGenerator: KeyGenerator
            try {
                keyGenerator = KeyGenerator
                        .getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
            } catch (e: NoSuchAlgorithmException) {
                throw RuntimeException("Failed to get an instance of KeyGenerator", e)

            } catch (e: NoSuchProviderException) {
                throw RuntimeException("Failed to get an instance of KeyGenerator", e)
            }

            try {
                keystore.load(null)
                keyGenerator.init(KeyGenParameterSpec.Builder("KEY_SORE", KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                        .setUserAuthenticationRequired(true)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                        .build())
                keyGenerator.generateKey()
            } catch (e: Exception) {
                Log.e("KeyStore", e.message)
                return
            }

            val defaultCipher: Cipher

            try {
                defaultCipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES
                        + "/" + KeyProperties.BLOCK_MODE_CBC
                        + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7)

            } catch (e: Exception) {
                Log.e("Cipher", e.message)
                return
            }

            try {
                keystore.load(null)
                val key: SecretKey? = keystore.getKey("KEY_STORE", null) as SecretKey?
                defaultCipher.init(Cipher.ENCRYPT_MODE, key)
            } catch (e: Exception) {
                Log.e("Secret Key", e.message)
                return
            }

            /**
             * wrap rgw cipher object in the CryptoObject which is needed by the fingerprint manager for authentication
             */

            val cryptoObject = FingerprintManager.CryptoObject(defaultCipher)
            /**
             * call the authenticate manager to listen to the fingerprint enrolment
             */
            val cancelationSignal = CancellationSignal()
            Log.d("Finger", "Conselation signal initialised and listening")
            fingerprintManager.authenticate(cryptoObject, cancelationSignal, 0, AuthenticationHandler(), null)
        } else {
            Log.d("Finger", "No Hardware")

        }


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_fingerprint_login, container, false)
        view.dismissBtn.setOnClickListener({
            dismiss()
        })

        return view
    }

    fun hasHardwareAndEnrolled(fingerprintManager: FingerprintManager, keyguardManager: KeyguardManager): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager.isHardwareDetected && fingerprintManager.hasEnrolledFingerprints() && keyguardManager.isKeyguardSecure
        } else {
            Log.d("Finger", "TheVersion is not compatible")
            false
        }
    }


//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        println("the oncreateDialog executed")
//        val context = this.activity
//        val dialogView = layoutInflater.inflate(R.layout.fingerprint_login_main, null)
//
//        val builder = AlertDialog.Builder(context)
//                .setView(dialogView)
////                .setTitle("Fingerprint Settup")
////                .setMessage("You can access you app faster and easier using fingerprint access.")
//                .setPositiveButton("Enroll"){ dialog, which ->
//
////                    val fingerprintIcon = dialogView.fingerprintImg.setImageIcon(R.id.)
//
//                } //set the onclick listener on the lister part
//                .setNegativeButton("Later"){dialog, which ->
//
//                } //set onclick listen on the listener part
//                .show()
////        val dialog = builder.create()
//
//        return builder
//    }


}// Required empty public constructor
