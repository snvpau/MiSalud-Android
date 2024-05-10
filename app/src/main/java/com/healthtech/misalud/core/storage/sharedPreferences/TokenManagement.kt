package com.healthtech.misalud.core.storage.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object TokenManagement {
    private const val ACCESS_TOKEN = "ACCESS_TOKEN"
    private const val REFRESH_TOKEN = "REFRESH_TOKEN"
    private const val IS_LOGGED_IN = "IS_LOGGED_IN"

    private var prefs: SharedPreferences? = null

    fun init(context: Context){
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        prefs = EncryptedSharedPreferences.create(
            context,
            "MiSaludAuth",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    var accessToken: String?
        get() = prefs?.getString(ACCESS_TOKEN, null)
        set(value) {
            prefs?.edit()?.apply {
                putString(ACCESS_TOKEN, value).apply()
                putBoolean(IS_LOGGED_IN, true)
            }
        }

    var refreshToken: String?
        get() = prefs?.getString(REFRESH_TOKEN, null)
        set(value) {
            prefs?.edit()?.putString(REFRESH_TOKEN, value)?.apply()
        }

    fun removeContents(){
        prefs?.edit()?.clear()?.apply()
    }

}