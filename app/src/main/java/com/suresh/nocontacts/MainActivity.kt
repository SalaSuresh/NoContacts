package com.suresh.nocontacts

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.suresh.nocontacts.databinding.ActivityMainBinding
import com.suresh.nocontacts.ui.calls.CallLogReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_favourites, R.id.navigation_no_contacts, R.id.navigation_calls
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //        authenticateUser()

    }

    private fun authenticateUser() {
        val executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence,
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult,
                ) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(
                        applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
//                textInfo.text = "App can authenticate using biometrics."
                biometricPrompt.authenticate(promptInfo)
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Log.e("MY_APP_TAG", "No biometric features available on this device.")
//                textInfo.text = "No biometric features available on this device."
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
//                textInfo.text = "Biometric features are currently unavailable."
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                // Prompts the user to create credentials that your app accepts.
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(
                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                    )
                }
                startActivityForResult(enrollIntent, 100)
            }

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
//                textInfo.text = "BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED"
            }

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
//                textInfo.text = "BIOMETRIC_ERROR_UNSUPPORTED"
            }

            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
//                textInfo.text = "BIOMETRIC_STATUS_UNKNOWN"
            }
        }
    }
}