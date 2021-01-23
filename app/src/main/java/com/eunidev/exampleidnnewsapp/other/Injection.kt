package com.eunidev.exampleidnnewsapp.other

import androidx.lifecycle.ViewModelProvider
import com.eunidev.exampleidnnewsapp.api.APIClient
import com.eunidev.exampleidnnewsapp.data.NewsRemoteDataSource
import com.eunidev.exampleidnnewsapp.model.NewsDataSource
import com.eunidev.exampleidnnewsapp.model.NewsRepository
import com.eunidev.exampleidnnewsapp.viewModel.ViewModelFactory

object Injection {

    private val newsDataSource: NewsDataSource = NewsRemoteDataSource(APIClient)
    private val newsRepository = NewsRepository(newsDataSource)
    private val newsViewModelFactory = ViewModelFactory(newsRepository)

    fun providerRepository(): NewsDataSource { return newsDataSource }
    fun provideViewModelFactory(): ViewModelProvider.Factory { return newsViewModelFactory }
}