package com.healthtech.misalud.core.network.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofitAuth() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://187.161.17.0:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitPeople() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ms-people.vercel.app")
            .client(createPeopleAuth())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createPeopleAuth(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        okHttpClientBuilder.addInterceptor(PeopleInterceptor(this))
        return okHttpClientBuilder.build()
    }
}