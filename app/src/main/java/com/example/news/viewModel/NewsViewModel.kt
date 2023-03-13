package com.example.news.viewModel

import androidx.lifecycle.ViewModel
import com.example.news.repository.NewsRepository
import com.example.news.response.NewsResponse

class NewsViewModel(
    private val repository: NewsRepository = NewsRepository()
):ViewModel() {

    fun getNews(
        successCallBack:(response:NewsResponse?) ->Unit
    ){
        repository.getNews {
            successCallBack(it)
        }
    }
}