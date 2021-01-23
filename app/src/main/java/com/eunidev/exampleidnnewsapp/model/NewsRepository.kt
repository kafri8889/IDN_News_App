package com.eunidev.exampleidnnewsapp.model

import com.eunidev.exampleidnnewsapp.data.NewsResponse
import com.eunidev.exampleidnnewsapp.data.OperationCallback
import com.eunidev.exampleidnnewsapp.database.NewsArticles

class NewsRepository(private val newsDataSource: NewsDataSource) {

    fun fetchNewsData(callback: OperationCallback<List<NewsArticles>>) {
        newsDataSource.retrieveNews(callback)
    }

    fun cancel() {
        newsDataSource.cancel()
    }
}