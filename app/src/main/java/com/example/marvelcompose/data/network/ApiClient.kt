package com.example.marvelcompose.data.network

import com.example.marvelcompose.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date

const val API_ENDPOINT = "https://gateway.marvel.com/"

object ApiClient {

    private val logginInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logginInterceptor)
        .addInterceptor(QueryInterceptor())
        .build()

    private val restAdapter = Retrofit.Builder()
        .baseUrl(API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val charactersService: CharactersService = restAdapter.create(CharactersService::class.java)
}

private class QueryInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val ts = Date().time
        val hash = generateHash(ts, "2feac25ed7d3bae7afbaa3f5951914f077b2f268", "cae244b8ce238babe08de4a9408d4a35")

        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", "cae244b8ce238babe08de4a9408d4a35")
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", hash)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}