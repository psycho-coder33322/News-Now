package com.akshatsahijpal.newsnow.api

import com.akshatsahijpal.newsnow.data.NewsData
import com.akshatsahijpal.newsnow.data.NewsRefinedData
import com.akshatsahijpal.newsnow.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    companion object{
        var BASE_URL = "https://newsapi.org/v2/"
        var KEY = Constants.API_KEY
    }
    @GET("everything")
    suspend fun getEveryNews(
        @Query("q") searchParameter: String,
        @Query("apiKey") authKey: String = KEY
    ) : NewsData


    @GET("everything")
    suspend fun getEveryNewsForParameterQ(
        @Query("q") searchParameter: String,
        @Query("apiKey") authKey: String = KEY
    ) : List<NewsRefinedData>
}