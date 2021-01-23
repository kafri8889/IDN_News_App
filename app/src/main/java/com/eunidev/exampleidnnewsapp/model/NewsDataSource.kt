package com.eunidev.exampleidnnewsapp.model

import com.eunidev.exampleidnnewsapp.data.NewsResponse
import com.eunidev.exampleidnnewsapp.data.OperationCallback
import com.eunidev.exampleidnnewsapp.database.NewsArticles

interface NewsDataSource {
    fun retrieveNews(callback: OperationCallback<List<NewsArticles>>)
    fun cancel()
}