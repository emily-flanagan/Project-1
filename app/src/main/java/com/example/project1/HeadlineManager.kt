package com.example.project1
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
    // function to return top headlines based on a category (uses general as the default if nothing is selected)
    suspend fun retrieveHeadlines(category: String, apiKey: String): List<HeadlineData> {
        // the actual request to get the headlines from the NewsAPI
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
            // loop through articles returned from the api and parse them out (title, source, image, url, author, description)
            for (i in 0 until article.length()) {
                val current = article.getJSONObject(i)
                val name = current.getString("title")
                // sources are an object so have to create the object and then retrieve the name
                val sourceObject = current.getJSONObject("source")
                val currentSource=sourceObject.getString("name")
                val img = current.getString("urlToImage")
                val currentURL = current.getString("url")
                val currentAuthor = current.getString("author")
                val currentDescription = current.getString("description")
                // put the article back together as HeadlineData in order to display on the screen
                val headline=HeadlineData(
                    title = name,
                    author = currentAuthor,
                    description = currentDescription,
                    source = currentSource,
                    image = img,
                    url = currentURL
                )
                // add the article to the headlines list
                headlines.add(headline)
            }
            return headlines
        }
        else {
            return listOf()
        }
        //Log.d("httpResponse", "response is $response and body is $responseBody")
    }
// Function to return values based on search term entered by user
    suspend fun retrieveSearch(searchTerm: String?, apiKey: String): List<HeadlineData> {
        // actual request to get the headlines from the NewsAPI
        val request = Request.Builder()
            .url("https://newsapi.org/v2/everything?q=$searchTerm&apiKey=$apiKey")
            .get()
            .addHeader("Authorization", apiKey)
            .build()

        val response=okHttpClient.newCall(request).execute()
        val responseBody=response.body?.string()
        if(response.isSuccessful && !responseBody.isNullOrEmpty()) {
            val headlines=mutableListOf<HeadlineData>()
            val json= JSONObject(responseBody)
            val article = json.getJSONArray("articles")
            // loop through articles returned from the api and parse them out (title, source, image, url, author, description)
            for (i in 0 until article.length()) {
                val current = article.getJSONObject(i)
                val name = current.getString("title")
                // sources are an object so have to create the object and then retrieve the name
                val sourceObject = current.getJSONObject("source")
                val currentSource=sourceObject.getString("name")
                val img = current.getString("urlToImage")
                val currentURL = current.getString("url")
                val currentAuthor = current.getString("author")
                val currentDescription = current.getString("description")
                // put the article back together as HeadlineData in order to display on the screen
                val headline=HeadlineData(
                    title = name,
                    author = currentAuthor,
                    description = currentDescription,
                    source = currentSource,
                    image = img,
                    url = currentURL
                )
                // add the article to the headlines list
                headlines.add(headline)
            }
            return headlines
        }
        else {
            return listOf()
        }
        //Log.d("httpResponse", "response is $response and body is $responseBody")
    }

    /*// Function to return sources based on dropdown choice by user (default is general)
    suspend fun retrieveSources(category: String, apiKey: String): List<SourceData> {
        // actual request to get the sources from the NewsAPI
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines/sources?category=$category&apiKey=$apiKey")
            .get()
            .addHeader("Authorization", apiKey)
            .build()

        val response=okHttpClient.newCall(request).execute()
        val responseBody=response.body?.string()
        if(response.isSuccessful && !responseBody.isNullOrEmpty()) {
            val headlines=mutableListOf<HeadlineData>()
            val json= JSONObject(responseBody)
            val sources = json.getJSONArray("sources")
            // loop through sources returned from the api and parse them out (title, source, image, url, author, description)
            for (i in 0 until sources.length()) {
                val current = sources.getJSONObject(i)
                val sourceName = current.getString("name")
                val sourceDescription = current.getString("description")
                val sourceURL = current.getString("url")
                // put the article back together as HeadlineData in order to display on the screen
                val source=SourceData(
                    name = sourceName,
                    description = sourceDescription,
                    url = sourceURL
                )
                // add the article to the headlines list
                sources.add(source)
            }
            return sources
        }
        else {
            return listOf()
        }
        //Log.d("httpResponse", "response is $response and body is $responseBody")
    }*/
}

