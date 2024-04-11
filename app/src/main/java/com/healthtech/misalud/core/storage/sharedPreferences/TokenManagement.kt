package com.healthtech.misalud.core.storage.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class TokenManagement(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "MiSaludAuth",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveAccessToken(token: String) {
        prefs.edit().putString("accessToken", token).apply()
    }

    fun getAccessToken(): String? {
        return prefs.getString("accessToken", null)
    }

    fun saveRefreshToken(token: String) {
        prefs.edit().putString("refreshToken", token).apply()
    }

    fun getRefreshToken(): String? {
        return prefs.getString("refreshToken", null)
    }

    fun removeContents(){
        prefs.edit().clear().apply()
    }

}