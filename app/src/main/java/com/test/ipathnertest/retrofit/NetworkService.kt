package com.test.ipathnertest.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {

    val baseURL= "https://bnet.i-partner.ru/testAPI/"
    val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getJsonApi(): EntriesAPI {
        return retrofit.create(EntriesAPI::class.java)
    }
}