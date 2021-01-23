package com.eunidev.exampleidnnewsapp.api

import com.eunidev.exampleidnnewsapp.data.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("top-headlines?country=id&category=technology&apiKey=feec96ee0bea4eb89ac16638b84de694")
    fun getNews(): Call<NewsResponse>
}