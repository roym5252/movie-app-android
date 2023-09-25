package com.movieapp.core.util.biometric

import androidx.biometric.BiometricPrompt

/**
 * Listeners for biometric authentication result.
 */
interface BiometricAuthListener {
    fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult)
    fun onBiometricAuthenticationError(errorCode: Int, errorMessage: String)
}