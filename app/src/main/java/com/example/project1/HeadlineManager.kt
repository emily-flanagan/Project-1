package com.example.project1
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

class HeadlineManager {
    val okHttpClient: OkHttpClient
    init{
        val builder=OkHttpClient.Builder()
        val loggingInterceptor=HttpLoggingInterceptor()
        loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)

        okHttpClient=builder.build()
    }
    suspend fun retrieveHeadlines(category: String, apiKey: String): List<HeadlineData> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=us&category=$category&apiKey=$apiKey")
            .get()
            .addHeader("Authorization", apiKey)
            .build()

        val response=okHttpClient.newCall(request).execute()
        val responseBody=response.body?.string()
        Log.d("httpResponse", "response is $response and body is $responseBody")

        return listOf()
    }
}

