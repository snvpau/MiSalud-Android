package com.healthtech.misalud.core.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofitAuth() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.100.76:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitPeople() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.100.76:5001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}