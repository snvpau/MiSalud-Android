package com.healthtech.misalud.core.storage.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object UserManagement {

    private var prefs: SharedPreferences? = null
    fun init(context: Context){
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        prefs = EncryptedSharedPreferences.create(
            context,
            "MiSaludPeople",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveUserAttributeString(key: String, value: String) {
        prefs?.edit()?.putString(key, value)?.apply()
    }

    fun saveUserAttributeInt(key: String, value: Int) {
        prefs?.edit()?.putInt(key, value)?.apply()
    }

    fun getUserAttributeString(key: String): String? {
        return prefs?.getString(key, null)
    }

    fun getUserAttributeInt(key: String): Int? {
        return prefs?.getInt(key, -1)
    }

    fun removeContents(){
        prefs?.edit()?.clear()?.apply()
    }
}