package com.example.news.repository

import android.util.Log
import com.example.news.api.NewsWebService
import com.example.news.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(
    private val webService: NewsWebService = NewsWebService()
) {
    fun getNews(successCallback :(response : NewsResponse?) ->Unit){
        return webService.getNews().enqueue(object : Callback<NewsResponse?> {
            override fun onResponse(
                call: Call<NewsResponse?>,
                response: Response<NewsResponse?>
            ) {
                if (response.isSuccessful)
                    successCallback(response.body())
            }

            override fun onFailure(call: Call<NewsResponse?>, t: Throwable) {
                Log.d("Error",t.message.toString())
            }
        })
    }
}