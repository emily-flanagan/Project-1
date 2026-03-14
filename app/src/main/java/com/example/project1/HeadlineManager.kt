package com.example.project1
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import kotlin.String

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
        if(response.isSuccessful && !responseBody.isNullOrEmpty()) {
            val headlines=mutableListOf<HeadlineData>()
            val json= JSONObject(responseBody)
            val article = json.getJSONArray("articles")
            for (i in 0 until article.length()) {
                val current = article.getJSONObject(i)
                val name = current.getString("title")
                val sourceObject = current.getJSONObject("source")
                val currentSource=sourceObject.getString("name")
                val img = current.getString("urlToImage")
                val currentURL = current.getString("url")
                val currentAuthor = current.getString("author")
                val currentDescription = current.getString("description")

                val headline=HeadlineData(
                    title = name,
                    author = currentAuthor,
                    description = currentDescription,
                    source = currentSource,
                    image = img,
                    url = currentURL
                )
                headlines.add(headline)
            }
            return headlines
        }
        else {
            return listOf()
        }
        //Log.d("httpResponse", "response is $response and body is $responseBody")
    }
}

