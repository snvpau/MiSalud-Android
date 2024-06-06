package com.healthtech.misalud.core.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

object Navigation {

    @SuppressLint("StaticFieldLeak")
    var controller: NavHostController? = null
    @Composable
    fun Init(){
        controller = rememberNavController()
    }
}