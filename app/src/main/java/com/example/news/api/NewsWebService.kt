package com.example.news.api

import com.example.news.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

const val BASE_URL = "https://newsapi.org/v2/"

class NewsWebService {
    private var api :NewsApi

    init {
        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()

        api = retrofit.create(NewsApi::class.java)
    }
    fun getNews():Call<NewsResponse>{
        return api.getNews()
    }
}

interface NewsApi {
    @GET("everything?q=technology&apiKey=11bbedc333bc43c9bf77d18ef103c10d")
    fun getNews(): Call<NewsResponse>
}