package com.healthtech.misalud.core.network.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofitAuth() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ms-auth-seven.vercel.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitPeople() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ms-people.vercel.app")
            //.baseUrl("http://localhost:5001/api/v1/people/exercises?uuid=d9916645-a2e8-4a76-bb11-0c76a47faef2&range=2024-05-29")
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